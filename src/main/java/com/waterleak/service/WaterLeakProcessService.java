package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.dto.AckNbiotDto;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessService {

    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository wateruserRepository;
    private final AckNbiotRepository ackNbiotRepository;

    public List<MtdWaterLeakExamWateruser> getNotYetStartExamWaterUsers(){
        List<MtdWaterLeakExamWateruser> leakExamReadyWaterUsers = new ArrayList<MtdWaterLeakExamWateruser>();
        List<MtdWaterLeakExamGroup> MtdWaterLeakExamReadyGroups = groupRepository.findAllByExamStatus(Globals.WATERLEAK_STATUS_READY);
        for (MtdWaterLeakExamGroup mtdWaterLeakExamReadyGroup : MtdWaterLeakExamReadyGroups) {
            leakExamReadyWaterUsers.addAll(mtdWaterLeakExamReadyGroup.getLeakWaterUsers());
        }
        return leakExamReadyWaterUsers;
    }

    public AckNbiotDto getAckNbiotBy(String imei){
        Optional<AckNbiot> AckNbiotById = ackNbiotRepository.findById(imei);
        if(!AckNbiotById.isPresent())
             throw new RuntimeException("no exist imei");
        return AckNbiotById.get().convertToDto();
    }

}

