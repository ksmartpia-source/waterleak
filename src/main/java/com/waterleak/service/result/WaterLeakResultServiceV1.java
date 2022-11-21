package com.waterleak.service.result;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WaterLeakResultServiceV1 implements WaterLeakResultService {
  private final MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
  private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

  @Override
  public void decision(long examWateruserIdx) {
    MtdWaterLeakExamWateruser leaker = leakExamWateruserRepository
        .findById(examWateruserIdx).get();

  }
}
