package com.waterleak.schedule;

import com.waterleak.service.WaterLeakFinishAfterService;
import com.waterleak.service.WaterLeakProcessFinishService;
import com.waterleak.service.WaterLeakProcessStartService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaterLeakSchedule {
    private final WaterLeakProcessStartService startService;
    private final WaterLeakProcessFinishService finishService;
    private final WaterLeakFinishAfterService cleanService;

    @Scheduled(cron = "0 0/15 * * * *")
    public void start() {
        startService.startWaterLeakExam();
    }

    @Scheduled(cron = "0 10/15 * * * *")
    public void finish() {
        finishService.finishWaterLeakExam();
    }

    @Scheduled(cron = "0 20/15 * * * *")
    public void clean() {
        cleanService.restorationAfterWaterLeakExam();
    }
}