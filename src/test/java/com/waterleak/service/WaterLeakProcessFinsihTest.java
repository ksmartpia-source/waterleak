package com.waterleak.service;

import com.waterleak.WaterLeak;
import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class WaterLeakProcessFinsihTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository leakWateruserRepository;
    @Autowired private WaterLeakProcessFinishService finishService;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private WaterLeakFinishAfterService finishAfterService;

    @Test
    @Transactional
    public void 상태가_S_이고_검사종료일이_도래한경우() {
        //given
        MtdWaterLeakExamGroup willFinishGroup = groupRepository.findById(51L).get();
        willFinishGroup.updateExamFinishiedDt(LocalDateTime.now().minusDays(1L));
        //when
        Boolean result = finishService.isReadyToFinish(willFinishGroup);
        //then
        assertEquals(true, result);
    }

    @Test
    @Transactional
    public void 누수점검_종료_테스트() {
        //given
        MtdWaterLeakExamGroup willFinishGroup = groupRepository.findById(41L).get();
        willFinishGroup.updateExamFinishiedDt(LocalDateTime.now().minusDays(1L));
        //when
        finishService.finishWaterLeakExam(willFinishGroup);
        //then
        MtdWaterLeakExamGroup finishedGroup = groupRepository.findById(41L).get();
        List<MtdWaterLeakExamWateruser> leakers = leakWateruserRepository
                .findAllByExamGroup(finishedGroup);
        for (MtdWaterLeakExamWateruser leaker : leakers) {
            Optional<AckNbiot> byId = ackNbiotRepository.findById(leaker.getImei());
            assertTrue(byId.isPresent());
            assertEquals(Globals.NB_INSTRUCTION_TO_60, byId.get().getNbInstruction());
        }
    }

    @Test
    @Transactional
    public void 점검이완료된_수용가들의_60분_주기변경여부확인_테스트() {
        //when
        finishAfterService.restorationAfterWaterLeakExam();
        //then
        Optional<AckNbiot> byId = ackNbiotRepository.findById("864447051301461");
        assertFalse(byId.isPresent());
    }
}