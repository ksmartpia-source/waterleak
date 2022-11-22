package com.waterleak.restcontroller;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dto.AckNbiotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.waterleak.config.Globals.*;

@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class WaterLeakRestController {
    private final AckNbiotRepository ackNbiotRepository;

    @PostMapping(value = "add-instruction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity greeting(@RequestBody AckNbiotDto ackNbiotDto) {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        ackNbiotRepository.save(ackNbiotDto.convertToEntity());
        responseMap.put(MESSAGE, SUCCESS_ADD_MODEM_MESSAGE);
        responseMap.put(STATUS, HttpStatus.CREATED.value());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseMap)
                ;
    }
}
