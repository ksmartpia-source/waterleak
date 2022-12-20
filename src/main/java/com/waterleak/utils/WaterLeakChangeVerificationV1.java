package com.waterleak.utils;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import java.sql.Time;
import java.util.Stack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.waterleak.config.Globals.CHECK_LIST_SIZE;
import static com.waterleak.config.Globals.RESULT_TRUE_COUNT;

@Component
@RequiredArgsConstructor
public class WaterLeakChangeVerificationV1 implements WaterLeakChangeVerification {
  private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

  public boolean isCycleChangeVerification(String imei, Long cycle) {
    List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository.findTop10ByImeiOrderByMeteringDateAsc(imei);

    int checkListSize = seoulNbiots.size();
    if (checkListSize < CHECK_LIST_SIZE) {
      return false;
    }
    Stack<Timestamp> meteringDates = new Stack<>();
    seoulNbiots.forEach(i -> meteringDates.push(i.getMeteringDate()));

    List<Boolean> resultList = new ArrayList<>();
    for (int i = 0; i < checkListSize - 1; i++) {
      long diffMin = (meteringDates.pop().getTime() - meteringDates.peek().getTime()) / 60000; //분 차이
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
