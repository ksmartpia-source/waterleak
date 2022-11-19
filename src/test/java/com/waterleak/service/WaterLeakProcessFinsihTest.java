package com.waterleak.service;

import com.waterleak.WaterLeak;
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
    @Autowired private WaterLeakProcessFinishService finishService;

    @Test
    @Transactional
    public void 상태가_S_이고_검사종료일이_도래한경우() {
        //given
        //when
        //then
    }
}