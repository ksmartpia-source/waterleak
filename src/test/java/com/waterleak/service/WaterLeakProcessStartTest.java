package com.waterleak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.waterleak.WaterLeak;
import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import java.time.LocalDateTime;
import java.util.List;
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
public class WaterLeakProcessStartTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository wateruserRepository;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private WaterLeakProcessStartService leakProcessService;

    @Test
    @Transactional
    public void 누수점검_시작_테스트() {
        //given
        MtdWaterLeakExamGroup group = groupRepository.findById(1L).get();
        //when
        leakProcessService.startWaterLeakExam(group);
        //then
        MtdWaterLeakExamGroup startedGroup = groupRepository.findById(1L).get();
        assertEquals(Globals.WATERLEAK_STATUS_START, startedGroup.getExamStatus());
        assertNotNull(startedGroup.getExamPlanStartDt());
        assertNotNull(startedGroup.getExamStartedDt());
        assertNotNull(startedGroup.getExamFinishiedDt());
    }

    @Test
    @Transactional
    public void 상태가_R_이고_모든_수용가의_단말기_10분_주기변경이_성공한_경우() {
        //given
        MtdWaterLeakExamGroup group = groupRepository.findById(1L).get();
        //when
        Boolean result = leakProcessService.isReadyToStart(group);
        //then
        assertEquals(true, result);
        MtdWaterLeakExamGroup savedGroup = groupRepository.findById(1L).get();
        List<MtdWaterLeakExamWateruser> leakWaterUsers = wateruserRepository.findAllByExamGroup(savedGroup);
        for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
            assertEquals(Globals.WATERLEAK_STATUS_CHANGE_10, leakWaterUser.getChangeStatus());
        }
    }

    @Test
    @Transactional
    public void 상태가_R_시작예정일이_도래_하지않은_몇몇의_수용가만_10분_주기변경이_성공한_경우() {
        //given
        MtdWaterLeakExamGroup group = groupRepository.findById(11L).get();
        //when
        Boolean result = leakProcessService.isReadyToStart(group);
        //then
        assertEquals(false, result);
        MtdWaterLeakExamGroup savedGroup = groupRepository.findById(11L).get();
        List<MtdWaterLeakExamWateruser> leakWaterUsers = wateruserRepository.findAllByExamGroup(savedGroup);
        for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
            if(leakWaterUser.getImei().equals("864700040730534")){
                assertNull(leakWaterUser.getChangeStatus());
            } else {
                assertEquals(leakWaterUser.getChangeStatus(), Globals.WATERLEAK_STATUS_CHANGE_10);
            }
        }
    }

    @Test
    @Transactional
    public void 상태가_R_시작예정일이_도래한_몇몇의_수용가만_10분_주기변경이_성공한_경우() {
        //given
        MtdWaterLeakExamGroup group = groupRepository.findById(11L).get();
        group.updateExamPlanStartDt(LocalDateTime.now().minusDays(1L));
        //when
        Boolean result = leakProcessService.isReadyToStart(group);
        //then
        assertEquals(true, result);
        MtdWaterLeakExamGroup savedGroup = groupRepository.findById(11L).get();
        List<MtdWaterLeakExamWateruser> leakWaterUsers = wateruserRepository.findAllByExamGroup(savedGroup);
        int changeSuccessCnt = 0;
        int changeFailCnt = 0;
        for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
            if(leakWaterUser.getChangeStatus().equals(Globals.WATERLEAK_STATUS_CHANGE_10))
                changeSuccessCnt ++;
            if(leakWaterUser.getChangeStatus().equals(Globals.WATERLEAK_STATUS_CHANGE_FAIL))
                changeFailCnt ++;
        }
        assertEquals(2, changeSuccessCnt);
        assertEquals(1, changeFailCnt);
    }

    @Test
    @Transactional
    public void 상태가_R_시작예정일이_도래한_모든수용가의_10분_주기변경이_실패_한경우() {
        //given
        MtdWaterLeakExamGroup group = groupRepository.findById(21L).get();
        group.updateExamPlanStartDt(LocalDateTime.now().minusDays(1L));
        //when
        Boolean result = leakProcessService.isReadyToStart(group);
        groupRepository.flush();
        //then
        assertEquals(false, result);
        MtdWaterLeakExamGroup savedGroup = groupRepository.findById(21L).get();
        assertEquals(Globals.WATERLEAK_STATUS_FINISH, savedGroup.getExamStatus());
        assertEquals(savedGroup.getExamStartedDt(), savedGroup.getExamFinishiedDt());
        List<MtdWaterLeakExamWateruser> leakWaterUsers = wateruserRepository.findAllByExamGroup(savedGroup);
        for (MtdWaterLeakExamWateruser leakWaterUser : leakWaterUsers) {
            assertEquals(Globals.WATERLEAK_STATUS_CHANGE_FAIL, leakWaterUser.getChangeStatus());
        }
    }
}