package com.waterleak.service;

import com.waterleak.config.Globals;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaterLeakProcessFinishService {

  private final MtdWaterLeakExamWateruserRepository wateruserRepository;

  public Boolean isReadyToFinish(MtdWaterLeakExamGroup group) {
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(group.getExamFinishiedDt())
        && group.getExamStatus().equals(Globals.WATERLEAK_STATUS_START)) {
      return true;
    }
    return false;
  }
}
