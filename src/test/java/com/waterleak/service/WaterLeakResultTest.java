package com.waterleak.service;

import static com.waterleak.config.Globals.WATERLEAK_RESULT_FINE_LEAK;
import static com.waterleak.config.Globals.WATERLEAK_RESULT_LEAK;
import static com.waterleak.config.Globals.WATERLEAK_RESULT_NORMAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.waterleak.WaterLeak;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdMeterinfoLeakRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.service.result.WaterLeakResultService;
import java.util.Optional;
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
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository leakExamWateruserRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private MtdMeterinfoLeakRepository meterinfoLeakRepository;
    @Autowired private WaterLeakResultService resultService;

    @Test
    @Transactional
    public void 누수점검_결론_도출_누수() {
        long examWateruserIdx = 72L;
        Optional<MtdWaterLeakExamGroup> byId = groupRepository.findById(71L);
        resultService.decision(byId.get(), examWateruserIdx);

        Optional<MtdWaterLeakExamWateruser> leakExamById = leakExamWateruserRepository
            .findById(examWateruserIdx);
        assertTrue(leakExamById.isPresent());
        MtdWaterLeakExamWateruser finishedLeaker = leakExamById.get();
        assertEquals(WATERLEAK_RESULT_LEAK, finishedLeaker.getExamResult());
        assertNotNull(finishedLeaker.getLeakMinUsage());
    }

    @Test
    @Transactional
    public void 누수점검_결론_도출_미세누수() {
        long examWateruserIdx = 82L;
        Optional<MtdWaterLeakExamGroup> byId = groupRepository.findById(81L);
        resultService.decision(byId.get(), examWateruserIdx);

        Optional<MtdWaterLeakExamWateruser> leakExamById = leakExamWateruserRepository
            .findById(examWateruserIdx);
        assertTrue(leakExamById.isPresent());
        MtdWaterLeakExamWateruser finishedLeaker = leakExamById.get();
        assertEquals(WATERLEAK_RESULT_FINE_LEAK, finishedLeaker.getExamResult());
        assertNotNull(finishedLeaker.getLeakMinUsage());
    }

    @Test
    @Transactional
    public void 누수점검_결론_도출_정상() {
        long examWateruserIdx = 92L;
        Optional<MtdWaterLeakExamGroup> byId = groupRepository.findById(91L);
        resultService.decision(byId.get(), examWateruserIdx);

        Optional<MtdWaterLeakExamWateruser> leakExamById = leakExamWateruserRepository
            .findById(examWateruserIdx);
        assertTrue(leakExamById.isPresent());
        MtdWaterLeakExamWateruser finishedLeaker = leakExamById.get();
        assertEquals(WATERLEAK_RESULT_NORMAL, finishedLeaker.getExamResult());
        assertNotNull(finishedLeaker.getLeakMinUsage());
    }

}