package com.waterleak.service.result;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import java.math.BigDecimal;
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
        List<MtdMeterinfoLeak> leaks = new ArrayList<>();

        int index = 0;
        for (MeterDataSeoulNbiot seoulNbiot : seoulNbiots) {
            if(index == seoulNbiots.size() - 1) {
                MtdMeterinfoLeak build = MtdMeterinfoLeak.builder()
                    .imei(seoulNbiot.getImei())
                    .meteringDate(seoulNbiot.getMeteringDate())
                    .meteringValue(seoulNbiot.getMeteringValue())
                    .meteringUsage(BigDecimal.valueOf(-1))
                    .build();
                leaks.add(build);
            } else {
                MtdMeterinfoLeak build = MtdMeterinfoLeak.builder()
                    .imei(seoulNbiot.getImei())
                    .meteringDate(seoulNbiot.getMeteringDate())
                    .meteringValue(seoulNbiot.getMeteringValue())
                    .meteringUsage(seoulNbiots.get(index).getMeteringValue().subtract(seoulNbiots.get(index+1).getMeteringValue()))
                    .build();
                leaks.add(build);
            }
            index++;
        }
        return leaks;
    }
}
