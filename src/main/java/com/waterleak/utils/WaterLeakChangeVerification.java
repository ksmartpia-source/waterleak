package com.waterleak.utils;

import static com.waterleak.config.Globals.CHECK_LIST_SIZE;
import static com.waterleak.config.Globals.RESULT_TRUE_COUNT;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaterLeakChangeVerification {
  private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

  public boolean isCycleChangeVerification(String imei, Long cycle) {
    List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository.findTop10ByImeiOrderByMeteringDateDesc(imei);
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
