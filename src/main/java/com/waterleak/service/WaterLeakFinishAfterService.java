package com.waterleak.service;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.utils.WaterLeakChangeVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.waterleak.config.Globals.CYCLE_60_MIN;
import static com.waterleak.config.Globals.NB_TCP_INSTRUCTION_TO_60;

@Service
@RequiredArgsConstructor
public class WaterLeakFinishAfterService {
    private final AckNbiotRepository ackNbiotRepository;
    private final WaterLeakChangeVerification changeVerification;

    public void restorationAfterWaterLeakExam() {
        List<AckNbiot> ackNbiots = ackNbiotRepository.findAllByNbInstruction(NB_TCP_INSTRUCTION_TO_60);
        for (AckNbiot ackNbiot : ackNbiots) {
            if (changeVerification.isCycleChangeVerification(ackNbiot.getImei(), CYCLE_60_MIN)) {
                ackNbiotRepository.deleteById(ackNbiot.getImei());
            }
        }
    }
}
