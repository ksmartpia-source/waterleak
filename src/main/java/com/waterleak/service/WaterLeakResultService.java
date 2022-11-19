package com.waterleak.service;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WaterLeakResultService {
  private final MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
  private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

  public void decision(long examWateruserIdx) {
    MtdWaterLeakExamWateruser leaker = leakExamWateruserRepository
        .findById(examWateruserIdx).get();

  }
}
