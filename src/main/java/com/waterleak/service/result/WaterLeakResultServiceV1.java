package com.waterleak.service.result;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WaterLeakResultServiceV1 implements WaterLeakResultService {
    private final MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
    private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

    @Override
    public void decision(MtdWaterLeakExamGroup group, long examWateruserIdx) {
        MtdWaterLeakExamWateruser leaker = leakExamWateruserRepository.findById(examWateruserIdx).get();
        Timestamp fromDt = Timestamp.valueOf(group.getExamStartedDt());
        Timestamp toDt = Timestamp.valueOf(group.getExamFinishiedDt());
        List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository
                .findAllByImeiAndMeteringDateBetweenOrderByMeteringDateDesc(leaker.getImei(), fromDt, toDt);
        System.out.println("seoulNbiots.size() = " + seoulNbiots.size());
    }
}
