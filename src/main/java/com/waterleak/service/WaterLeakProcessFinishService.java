package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessFinishService {
  private final MtdWaterLeakExamGroupRepository groupRepository;
  private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
  private final AckNbiotRepository ackNbiotRepository;

  @Transactional
  public void finishWaterLeakExam(MtdWaterLeakExamGroup group) {
    if(isReadyToFinish(group)){
      group.finishExam();
      MtdWaterLeakExamGroup finishedGroup = groupRepository.save(group);
      List<MtdWaterLeakExamWateruser> leakWaterUsers = leakWateruserRepository.findAllByExamGroup(finishedGroup);
      for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
        AckNbiot to60Instruction = AckNbiot
            .builder()
            .imei(leakWaterUser.getImei())
            .nbInstruction(Globals.NB_INSTRUCTION_TO_60)
            .build();
        ackNbiotRepository.save(to60Instruction);
      }
    }
  }

  public Boolean isReadyToFinish(MtdWaterLeakExamGroup group) {
    System.out.println("############################################");
    System.out.println("############################################");
    System.out.println("group.getExamStatus() = " + group.getExamStatus());
    System.out.println("############################################");
    System.out.println("############################################");
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(group.getExamFinishiedDt())
        && group.getExamStatus().equals(Globals.WATERLEAK_STATUS_START)) {
      return true;
    }
    return false;
  }
}
