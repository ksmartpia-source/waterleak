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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    if (isReadyToStart(group)) {
      group.startExam();
    }
    groupRepository.save(group);
  }

  public Boolean isReadyToStart(MtdWaterLeakExamGroup group) {
    int changeUsers = 0;
    List<MtdWaterLeakExamWateruser> leakers = leakWateruserRepository.findAllByExamGroup(group);
    for (MtdWaterLeakExamWateruser leaker : leakers) {
      if (isCycleChangeVerification(leaker.getImei(), Globals.CYCLE_10_MIN)) {
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

  private void allChangeFails(MtdWaterLeakExamGroup group, List<MtdWaterLeakExamWateruser> leakers) {
    for (MtdWaterLeakExamWateruser leaker : leakers) {
      leaker.updateChangeStatus(Globals.WATERLEAK_STATUS_CHANGE_FAIL);
    }
    group.failExam();
    groupRepository.save(group);
  }

  public boolean isCycleChangeVerification(String imei, Long cycle) {
    List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository
        .findTop10ByImeiOrderByMeteringDateDesc(imei);
    List<Timestamp> meteringDates = seoulNbiots.stream().map(MeterDataSeoulNbiot::getMeteringDate)
        .collect(Collectors.toList());
    int checkListSize = seoulNbiots.size();
    List<Boolean> resultList = new ArrayList<>();
    if (checkListSize < CHECK_LIST_SIZE) {
      return false;
    }

    for (int i = 0; i < checkListSize - 1; i++) {
      long diffMin =
          (meteringDates.get(i).getTime() - meteringDates.get(i + 1).getTime()) / 60000; //분 차이
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

