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
public class WaterLeakProcessTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository wateruserRepository;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private WaterLeakProcessStartService leakProcessService;

    @Test
    @Transactional
    public void 일부_단말기의_60분_주기변경실패시_로직_테스트() {
    }

    @Test
    @Transactional
    public void 점검이완료된_수용가들의_60분_주기변경여부확인_테스트() {
    }

    @Test
    @Transactional
    public void 검침데이터_10분단위_를바탕으로_사용량을_계산_테스트() {
    }
}