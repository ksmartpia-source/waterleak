package com.waterleak.service.result;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WaterLeakResultServiceV1 implements WaterLeakResultService {
    private final MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
    private final MeterDataSeoulNbiotRepository seoulNbiotRepository;

    @Override
    public void decision(MtdWaterLeakExamGroup group, long examWateruserIdx) {
        MtdWaterLeakExamWateruser leaker = leakExamWateruserRepository.findById(examWateruserIdx).get();
        List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository
                .findAllByImeiAndMeteringDateBetweenOrderByMeteringDateDesc(
                        leaker.getImei(),
                        group.getExamStartedTimeStampDt(),
                        group.getExamFinishedTimeStampDt());

        for (MeterDataSeoulNbiot seoulNbiot : seoulNbiots) {
            System.out.println("seoulNbiot.getImei() = " + seoulNbiot.getImei());
            System.out.println("seoulNbiot.getMeteringDate() = " + seoulNbiot.getMeteringDate());
            System.out.println("seoulNbiot.getMeteringValue() = " + seoulNbiot.getMeteringValue());
        }

        List<MtdMeterinfoLeak> leaks = new ArrayList<MtdMeterinfoLeak>();
        leaks = calculateUsage(seoulNbiots);

    }

    public List<MtdMeterinfoLeak> calculateUsage(List<MeterDataSeoulNbiot> seoulNbiots) {
        return null;
    }
}
