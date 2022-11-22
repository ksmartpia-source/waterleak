package com.waterleak.service.result;

import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdMeterinfoLeakRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WaterLeakResultServiceV1 implements WaterLeakResultService {
    private final MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
    private final MeterDataSeoulNbiotRepository seoulNbiotRepository;
    private final MtdMeterinfoLeakRepository meterinfoLeakRepository;

    @Override
    @Transactional
    public void decision(MtdWaterLeakExamGroup group, long examWateruserIdx) {
        MtdWaterLeakExamWateruser leaker = leakExamWateruserRepository.findById(examWateruserIdx).get();
        List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository
                .findAllByImeiAndMeteringDateBetweenOrderByMeteringDateDesc(
                        leaker.getImei(),
                        group.getExamStartedTimeStampDt(),
                        group.getExamFinishedTimeStampDt());
        List<MtdMeterinfoLeak> savedMeterInfoLeaks = calculateUsages(seoulNbiots);
        determine(savedMeterInfoLeaks, leaker);
    }

    private void determine(List<MtdMeterinfoLeak> savedMeterInfoLeaks, MtdWaterLeakExamWateruser leaker) {
        int leakCount = 0;
        BigDecimal leakMinUsage = BigDecimal.valueOf(Integer.MAX_VALUE);
        BigDecimal zero = BigDecimal.ZERO;
        for (MtdMeterinfoLeak savedMeterInfoLeak : savedMeterInfoLeaks) {
            if (zero.compareTo(savedMeterInfoLeak.getMeteringUsage()) == 0) {
                leakCount++;
            }
            if (savedMeterInfoLeak.getMeteringUsage().compareTo(zero) > 0
                    && savedMeterInfoLeak.getMeteringUsage().compareTo(leakMinUsage) < 0) {
                leakMinUsage = savedMeterInfoLeak.getMeteringUsage();
            }
        }
        leaker.determinResult(leakCount, leakMinUsage);
        leakExamWateruserRepository.save(leaker);
    }

    public List<MtdMeterinfoLeak> calculateUsages(List<MeterDataSeoulNbiot> seoulNbiots) {
        List<MtdMeterinfoLeak> meterinfoLeaks = new ArrayList<MtdMeterinfoLeak>();
        int index = 0;
        for (MeterDataSeoulNbiot seoulNbiot : seoulNbiots) {
            MtdMeterinfoLeak meterinfoLeak = MtdMeterinfoLeak.builder()
                    .groupSid(6L)
                    .imei(seoulNbiot.getImei())
                    .meteringDate(seoulNbiot.getMeteringDate())
                    .meteringValue(seoulNbiot.getMeteringValue())
                    .meteringUsage(
                            index == seoulNbiots.size() - 1 ?
                                    BigDecimal.valueOf(-1) :
                                    seoulNbiots.get(index).getMeteringValue()
                                            .subtract(seoulNbiots.get(index + 1).getMeteringValue())
                    )
                    .build();
            meterinfoLeaks.add(meterinfoLeak);
            index++;
        }
        return meterinfoLeakRepository.saveAll(meterinfoLeaks);
    }
}
