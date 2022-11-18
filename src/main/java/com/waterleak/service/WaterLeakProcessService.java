package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.dto.AckNbiotDto;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.waterleak.config.Globals.*;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessService {

    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
    private final AckNbiotRepository ackNbiotRepository;
    private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

    @Transactional
    public List<MtdWaterLeakExamWateruser> getNotYetStartExamWaterUsers() {
        List<MtdWaterLeakExamWateruser> leakExamReadyWaterUsers = new ArrayList<MtdWaterLeakExamWateruser>();
        List<MtdWaterLeakExamGroup> MtdWaterLeakExamReadyGroups = groupRepository.findAllByExamStatus(WATERLEAK_STATUS_READY);
        for (MtdWaterLeakExamGroup mtdWaterLeakExamReadyGroup : MtdWaterLeakExamReadyGroups) {
            leakExamReadyWaterUsers.addAll(mtdWaterLeakExamReadyGroup.getLeakWaterUsers());
        }
        return leakExamReadyWaterUsers;
    }

    public AckNbiotDto getAckNbiotBy(String imei) {
        Optional<AckNbiot> AckNbiotById = ackNbiotRepository.findById(imei);
        if (!AckNbiotById.isPresent()) {
            throw new RuntimeException("no exist imei");
        }
        return AckNbiotById.get().convertToDto();
    }

    public boolean isCycleChangeVerification(String imei, Long cycle) {
        List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository.findTop10ByImeiOrderByMeteringDateDesc(imei);
        List<Timestamp> meteringDates = seoulNbiots.stream().map(MeterDataSeoulNbiot::getMeteringDate).collect(Collectors.toList());
        int checkListSize = seoulNbiots.size();
        List<Boolean> resultList = new ArrayList<>();

        if (checkListSize < CHECK_LIST_SIZE) {
            return false;
        }

        for (int i = 0; i < checkListSize - 1; i++) {
            long diffMin = (meteringDates.get(i).getTime() - meteringDates.get(i + 1).getTime()) / 60000; //분 차이
            resultList.add(cycle == diffMin);
        }

        int trueCount = 0;
        for (Boolean result : resultList) {
            if (result) {
                trueCount++;
            }
        }
        return trueCount > RESULT_TRUE_COUNT;
    }

}

