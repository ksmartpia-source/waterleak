package com.waterleak.service;

import static com.waterleak.config.Globals.CYCLE_60_MIN;
import static com.waterleak.config.Globals.NB_INSTRUCTION_TO_60;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.utils.WaterLeakChangeVerification;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaterLeakFinishAfterService {

    private final AckNbiotRepository ackNbiotRepository;
    private final WaterLeakChangeVerification changeVerification;

    public void restorationAfterWaterLeakExam() {
        List<AckNbiot> ackNbiots = ackNbiotRepository.findAllByNbInstruction(NB_INSTRUCTION_TO_60);
        for(AckNbiot ackNbiot : ackNbiots) {
            if(changeVerification.isCycleChangeVerification(ackNbiot.getImei(), CYCLE_60_MIN)) {
                ackNbiotRepository.deleteById(ackNbiot.getImei());
            }
        }
    }

}
