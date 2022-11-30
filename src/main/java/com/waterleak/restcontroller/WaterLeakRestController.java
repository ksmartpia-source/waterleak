package com.waterleak.restcontroller;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dto.AckNbiotDto;
import com.waterleak.service.WaterLeakFinishAfterService;
import com.waterleak.service.WaterLeakProcessFinishService;
import com.waterleak.service.WaterLeakProcessStartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static com.waterleak.config.Globals.*;

@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class WaterLeakRestController {
    private final AckNbiotRepository ackNbiotRepository;
    private final WaterLeakProcessStartService startService;
    private final WaterLeakProcessFinishService finishService;
    private final WaterLeakFinishAfterService cleanService;

    @PostMapping(value = "add-instruction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addInstruction(@RequestBody AckNbiotDto ackNbiotDto) {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        ackNbiotRepository.save(ackNbiotDto.convertToEntity());
        responseMap.put(MESSAGE, SUCCESS_ADD_MODEM_MESSAGE);
        responseMap.put(STATUS, HttpStatus.CREATED.value());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseMap)
                ;
    }

    @PostMapping(value = "start-exam")
    public ResponseEntity startExam() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakRestController.startExam()");
        System.out.println("#####################################");
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        startService.startWaterLeakExam();
        responseMap.put(MESSAGE, SUCCESS_START_EXAM);
        responseMap.put(STATUS, HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMap)
                ;
    }

    @PostMapping(value = "finish-exam")
    public ResponseEntity finishExam() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakRestController.finishExam()");
        System.out.println("#####################################");
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        finishService.finishWaterLeakExam();
        responseMap.put(MESSAGE, SUCCESS_FINISH_EXAM);
        responseMap.put(STATUS, HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMap)
                ;
    }

    @PostMapping(value = "clean-data")
    public ResponseEntity cleanData() {
        System.out.println("#####################################");
        System.out.println("current-time : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("call -- WaterLeakRestController.cleanData()");
        System.out.println("#####################################");
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        cleanService.restorationAfterWaterLeakExam();
        responseMap.put(MESSAGE, CLEAN_COMPLETE);
        responseMap.put(STATUS, HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMap)
                ;
    }
}
