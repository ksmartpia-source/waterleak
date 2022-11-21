package com.waterleak.service.result;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;

public interface WaterLeakResultService {
    void decision(MtdWaterLeakExamGroup group, long examWateruserIdx);
}
