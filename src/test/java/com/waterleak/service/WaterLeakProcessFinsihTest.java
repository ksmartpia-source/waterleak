package com.waterleak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.waterleak.WaterLeak;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import java.time.LocalDateTime;
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
    @Autowired private WaterLeakProcessFinishService finishService;

    @Test
    @Transactional
    public void 상태가_S_이고_검사종료일이_도래한경우() {
        //given
        MtdWaterLeakExamGroup willFinishGroup = groupRepository.findById(41L).get();
        willFinishGroup.updateExamFinishiedDt(LocalDateTime.now().minusDays(1L));
        //when
        Boolean result = finishService.isReadyToFinish(willFinishGroup);
        //then
        assertEquals(true, result);
    }
}