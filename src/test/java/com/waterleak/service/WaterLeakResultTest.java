package com.waterleak.service;

import com.waterleak.WaterLeak;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdMeterinfoLeakRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.service.result.WaterLeakResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class WaterLeakResultTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private MtdMeterinfoLeakRepository meterinfoLeakRepository;
    @Autowired private WaterLeakResultService resultService;

    @Test
    @Transactional
    public void 누수점검_결론_도출() {
        long examWateruserIdx = 72L;
        Optional<MtdWaterLeakExamGroup> byId = groupRepository.findById(71L);
        resultService.decision(byId.get(), examWateruserIdx);

//        Optional<MtdWaterLeakExamWateruser> leakExamById = leakExamWateruserRepository
//            .findById(examWateruserIdx);
//        assertEquals(true, leakExamById.isPresent());
//        MtdWaterLeakExamWateruser finishedLeaker = leakExamById.get();
//        assertNotNull(finishedLeaker.getExamResult());
//        assertNotNull(finishedLeaker.getLeakMinUsage());
    }
}