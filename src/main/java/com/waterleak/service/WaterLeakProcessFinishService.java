package com.waterleak.service;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.service.result.WaterLeakResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.waterleak.config.Globals.*;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessFinishService {
    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
    private final AckNbiotRepository ackNbiotRepository;
    private final WaterLeakResultService resultService;

    @Transactional
    public void finishWaterLeakExam() {
        List<MtdWaterLeakExamGroup> allByExamStatus = groupRepository.findAllByExamStatus(WATERLEAK_STATUS_START);
        for (MtdWaterLeakExamGroup willFinishGroup : allByExamStatus) {
            finishWaterLeakExam(willFinishGroup);
        }
    }

    public void finishWaterLeakExam(MtdWaterLeakExamGroup group) {
        if (isReadyToFinish(group)) {
            group.finishExam();
            MtdWaterLeakExamGroup finishedGroup = groupRepository.save(group);
            List<MtdWaterLeakExamWateruser> leakWaterUsers = leakWateruserRepository.findAllByExamGroup(finishedGroup);
            for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
                AckNbiot to60Instruction = AckNbiot
                        .builder()
                        .imei(leakWaterUser.getImei())
                        .nbInstruction(getFinishInstruct(leakWaterUser))
                        .insertDate(LocalDateTime.now())
                        .build();
                leakWaterUser.updateChangeStatus(WATERLEAK_STATUS_CHANGE_60);
                leakWateruserRepository.save(leakWaterUser);
                ackNbiotRepository.save(to60Instruction);
                resultService.decision(group, leakWaterUser.getExamWateruserIdx());
            }
        }
    }

    public String getFinishInstruct(MtdWaterLeakExamWateruser leakWaterUser) {
        if (leakWaterUser.getCommType() == null
                || leakWaterUser.getCommType().equals(""))
            return FAIL;
        if (leakWaterUser.getCommType().equals(COMMUNICATION_TYPE_TCP))
            return NB_TCP_INSTRUCTION_TO_60;
        if (leakWaterUser.getCommType().equals(COMMUNICATION_TYPE_UDP))
            return NB_UDP_INSTRUCTION_TO_60;

        return FAIL;
    }

    public Boolean isReadyToFinish(MtdWaterLeakExamGroup group) {
        if (LocalDateTime.now().isAfter(group.getExamFinishiedDt())
                && group.getExamStatus().equals(WATERLEAK_STATUS_START)) {
            return true;
        }
        return false;
    }
}
