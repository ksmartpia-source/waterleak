package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WapiService {
    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository wateruserRepository;

    public List<MtdWaterLeakExamWateruser> getNotYetStartExamWaterUsers(){
        List<MtdWaterLeakExamWateruser> leakExamReadyWaterUsers = new ArrayList<MtdWaterLeakExamWateruser>();
        List<MtdWaterLeakExamGroup> MtdWaterLeakExamReadyGroups = groupRepository.findAllByExamStatus(Globals.WATERLEAK_STATUS_READY);
        for (MtdWaterLeakExamGroup mtdWaterLeakExamReadyGroup : MtdWaterLeakExamReadyGroups) {
            leakExamReadyWaterUsers.addAll(mtdWaterLeakExamReadyGroup.getLeakWaterUsers());
        }
        return leakExamReadyWaterUsers;
    }
}

