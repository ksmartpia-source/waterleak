package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.utils.WaterLeakChangeVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.waterleak.config.Globals.NB_TCP_INSTRUCTION_TO_60;
import static com.waterleak.config.Globals.WATERLEAK_STATUS_READY;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessStartService {
    private final MtdWaterLeakExamGroupRepository groupRepository;
    private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
    private final WaterLeakChangeVerification changeVerification;
    private final AckNbiotRepository ackNbiotRepository;

    @Transactional
    public void startWaterLeakExam() {
        List<MtdWaterLeakExamGroup> allByExamStatus = groupRepository.findAllByExamStatus(WATERLEAK_STATUS_READY);
        for (MtdWaterLeakExamGroup byExamStatus : allByExamStatus) {
            startWaterLeakExam(byExamStatus);
        }
    }

    public void startWaterLeakExam(MtdWaterLeakExamGroup group) {
        if (isReadyToStart(group)) {
            group.startExam();
            MtdWaterLeakExamGroup startedGroup = groupRepository.save(group);
            List<MtdWaterLeakExamWateruser> leakWaterUsers = leakWateruserRepository.findAllByExamGroup(startedGroup);
        }
    }

    public Boolean isReadyToStart(MtdWaterLeakExamGroup group) {
        int changeUsers = 0;
        List<MtdWaterLeakExamWateruser> leakers = leakWateruserRepository.findAllByExamGroup(group);
        for (MtdWaterLeakExamWateruser leaker : leakers) {
            if (changeVerification.isCycleChangeVerification(leaker.getImei(), Globals.CYCLE_10_MIN)) {
                leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_10);
                leakWateruserRepository.save(leaker);
                changeUsers++;
            }
        }
        if (changeUsers == leakers.size()) {
            return true;
        }
        if (changeUsers == 0) {
            if (LocalDateTime.now().isAfter(group.getExamPlanStartDt())) {
                allChangeFails(group, leakers);
            }
            return false;
        }
        if (changeUsers < leakers.size()) {
            if (LocalDateTime.now().isAfter(group.getExamPlanStartDt()) && leakers.size() > 0) {
                someChangeFails(group, leakers);
                return true;
            }
        }
        return false;
    }

    private void someChangeFails(MtdWaterLeakExamGroup group, List<MtdWaterLeakExamWateruser> leakers) {
        for (MtdWaterLeakExamWateruser leaker : leakers) {
            if (Objects.isNull(leaker.getChangeStatus()) ||
                    !leaker.getChangeStatus().equals(Globals.WATERLEAK_STATUS_CHANGE_10)) {
                leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_FAIL);
                leakWateruserRepository.save(leaker);
            }
        }
    }

    private void allChangeFails(MtdWaterLeakExamGroup group,
                                List<MtdWaterLeakExamWateruser> leakers) {
        for (MtdWaterLeakExamWateruser leaker : leakers) {
            leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_FAIL);
            leakWateruserRepository.save(leaker);
            AckNbiot to60Instruction = AckNbiot
                    .builder()
                    .imei(leaker.getImei())
                    .nbInstruction(NB_TCP_INSTRUCTION_TO_60)
                    .build();
            ackNbiotRepository.save(to60Instruction);
        }
        group.failExam();
        groupRepository.save(group);
    }
}
