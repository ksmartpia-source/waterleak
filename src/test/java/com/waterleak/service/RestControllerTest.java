package com.waterleak.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterleak.WaterLeak;
import com.waterleak.dto.AckNbiotDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class RestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void 주기변경_명령어_등록_테스트() throws Exception {
        AckNbiotDto ackNbiotDto = AckNbiotDto.builder()
                .imei("8123456789098765")
                .nbInstruction("QLI")
                .build();
        this.mockMvc
                .perform(
                        post("/add-instruction")
                                .content(mapper.writeValueAsString(ackNbiotDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}