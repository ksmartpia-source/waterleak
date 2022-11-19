package com.waterleak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.waterleak.WaterLeak;
import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class WaterLeakChangeCycleTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository wateruserRepository;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private WaterLeakProcessService leakProcessService;

    @Test
    @Transactional
    public void 주기변경_10분_검증_테스트() {
        //given
        String tenMinIMEI = "864447051283958";
        //when
        Boolean result = leakProcessService.isCycleChangeVerification(tenMinIMEI, Globals.CYCLE_10_MIN);
        //then
        assertEquals(true, result);
    }

    @Test
    @Transactional
    public void 주기변경_60분_검증_테스트() {
        //given
        String tenMinIMEI = "864700040744484";
        //when
        Boolean result = leakProcessService.isCycleChangeVerification(tenMinIMEI, Globals.CYCLE_60_MIN);
        //then
        assertEquals(true, result);
    }
}