package com.waterleak.service;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessFinishService {

  public Boolean isReadyToEnd(MtdWaterLeakExamGroup group) {

    return false;
  }
}
