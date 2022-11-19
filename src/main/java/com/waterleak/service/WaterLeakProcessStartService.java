package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.utils.WaterLeakChangeVerification;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessStartService {
  private final MtdWaterLeakExamGroupRepository groupRepository;
  private final MtdWaterLeakExamWateruserRepository leakWateruserRepository;
  private final WaterLeakChangeVerification changeVerification;
  private final AckNbiotRepository ackNbiotRepository;

  @Transactional
  public void startWaterLeakExam(MtdWaterLeakExamGroup group) {
    if (isReadyToStart(group)) {
      group.startExam();
      MtdWaterLeakExamGroup savedGroup = groupRepository.save(group);
      List<MtdWaterLeakExamWateruser> leakWaterUsers = leakWateruserRepository.findAllByExamGroup(savedGroup);
      for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
        ackNbiotRepository.delete(AckNbiot.builder().imei(leakWaterUser.getImei()).build());
      }
    }
  }

  public Boolean isReadyToStart(MtdWaterLeakExamGroup group) {
    int changeUsers = 0;
    List<MtdWaterLeakExamWateruser> leakers = leakWateruserRepository.findAllByExamGroup(group);
    for (MtdWaterLeakExamWateruser leaker : leakers) {
      if (changeVerification.isCycleChangeVerification(leaker.getImei(), Globals.CYCLE_10_MIN)) {
        leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_10);
        changeUsers++;
      }
    }
    if (changeUsers == leakers.size()) {
      return true;
    }
    if (changeUsers == 0) {
      allChangeFails(group, leakers);
      return false;
    }
    if (changeUsers < leakers.size()) {
      if (LocalDateTime.now().isAfter(group.getExamPlanStartDt()) && leakers.size() > 0) {
        someChangeFails(leakers);
        return true;
      }
    }
    return false;
  }

  private void someChangeFails(List<MtdWaterLeakExamWateruser> leakers) {
    for (MtdWaterLeakExamWateruser leaker : leakers) {
      if (Objects.isNull(leaker.getChangeStatus()) ||
          !leaker.getChangeStatus().equals(Globals.WATERLEAK_STATUS_CHANGE_10)) {
        leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_FAIL);
      }
    }
  }

  private void allChangeFails(MtdWaterLeakExamGroup group,
      List<MtdWaterLeakExamWateruser> leakers) {
    for (MtdWaterLeakExamWateruser leaker : leakers) {
      leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_FAIL);
    }
    group.failExam();
    groupRepository.save(group);
  }
}
