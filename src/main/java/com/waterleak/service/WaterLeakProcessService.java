package com.waterleak.service;

import static com.waterleak.config.Globals.CHECK_LIST_SIZE;
import static com.waterleak.config.Globals.RESULT_TRUE_COUNT;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessService {
    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
    private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

    @Transactional
    public void startWaterLeakExam(MtdWaterLeakExamGroup group) {
        if(isReadyToStart(group))
            group.changeGroupStatusWithStart();
        groupRepository.save(group);
    }

    @Transactional
    public Boolean isReadyToStart(MtdWaterLeakExamGroup group) {
        if (allUsersChangeSuccess(group)) {
            return true;
        }

        return false;
    }

    public boolean allUsersChangeSuccess(MtdWaterLeakExamGroup group) {
        int changeUsers = 0;
        List<MtdWaterLeakExamWateruser> leakWaterUsers = leakWateruserRepository.findAllByExamGroup(
            group);
        for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
            if (isCycleChangeVerification(leakWaterUser.getImei(), Globals.CYCLE_10_MIN))
                changeUsers++;
        }
        if (changeUsers == leakWaterUsers.size())
            return true;
        return false;
    }

    public boolean someUsersChangeSuccess(MtdWaterLeakExamGroup group) {

      return false;
    }

    @Transactional
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

