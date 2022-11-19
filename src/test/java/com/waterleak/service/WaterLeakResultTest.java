package com.waterleak.service;

import com.waterleak.WaterLeak;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
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
public class WaterLeakResultTest {
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;

    @Test
    @Transactional
    public void 누수점검_결론_도출() {
    }
}