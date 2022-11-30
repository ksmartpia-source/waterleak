package com.waterleak.schedule;

import com.waterleak.service.WaterLeakFinishAfterService;
import com.waterleak.service.WaterLeakProcessFinishService;
import com.waterleak.service.WaterLeakProcessStartService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class WaterLeakSchedule {
    private final WaterLeakProcessStartService startService;
    private final WaterLeakProcessFinishService finishService;
    private final WaterLeakFinishAfterService cleanService;

    @Scheduled(cron = "0 0/15 * * * *")
    public void start() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakSchedule.start()");
        System.out.println("#####################################");
        startService.startWaterLeakExam();
    }

    @Scheduled(cron = "0 10/15 * * * *")
    public void finish() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakSchedule.finish()");
        System.out.println("#####################################");
        finishService.finishWaterLeakExam();
    }

    @Scheduled(cron = "0 20/15 * * * *")
    public void clean() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakSchedule.clean()");
        System.out.println("#####################################");
        cleanService.restorationAfterWaterLeakExam();
    }
}