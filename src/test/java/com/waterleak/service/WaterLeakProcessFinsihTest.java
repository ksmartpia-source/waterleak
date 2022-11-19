package com.waterleak.service;

import com.waterleak.WaterLeak;
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
public class WaterLeakProcessFinsihTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository wateruserRepository;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private WaterLeakProcessStartService leakProcessService;

    @Test
    @Transactional
    public void 상태가_S_이고_검사종료일이_도래한경우() {
        //given
        //when
        //then
    }
}