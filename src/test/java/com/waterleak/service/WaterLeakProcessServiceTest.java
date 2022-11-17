package com.waterleak.service;

import com.waterleak.WaterLeak;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dao.reporting.MeterDataSeoulNbiotRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamGroupRepository;
import com.waterleak.dao.wapi.MtdWaterLeakExamWateruserRepository;
import com.waterleak.dto.AckNbiotDto;
import com.waterleak.model.reporting.AckNbiot;
import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class WaterLeakProcessServiceTest {
    @Autowired private MtdWaterLeakExamGroupRepository groupRepository;
    @Autowired private MtdWaterLeakExamWateruserRepository wateruserRepository;
    @Autowired private AckNbiotRepository ackNbiotRepository;
    @Autowired private MeterDataSeoulNbiotRepository seoulNbiotRepository;
    @Autowired private WaterLeakProcessService leakProcessService;

    @Test
    public void AckNbiot_단건_조회() {
        AckNbiot instruct = AckNbiot.builder()
                .imei("890123456719876")
                .nbInstruction("QSDF")
                .insertDate(Calendar.getInstance())
                .build();
        AckNbiot savedAckNbiot = ackNbiotRepository.save(instruct);
        final Optional<AckNbiot> result = ackNbiotRepository.findById(savedAckNbiot.getImei());
        assertTrue(result.isPresent());
        AckNbiotDto ackNbiotBy = leakProcessService.getAckNbiotBy(instruct.getImei());
        assertEquals(savedAckNbiot.getImei(), ackNbiotBy.getImei());
        assertEquals(savedAckNbiot.getNbInstruction(), ackNbiotBy.getNbInstruction());
    }

    @Test
    public void IMEI_활용_MeterDataSeoulNbiot_조회_테스트() {
        String targetImei = "864700040744484";
        List<MeterDataSeoulNbiot> seoulNbiots = seoulNbiotRepository.findAllByImeiOrderByMeteringDateDesc(targetImei);
        assertEquals(seoulNbiots.size(), 20);
        assertEquals(seoulNbiots.get(0).getImei(), targetImei);
    }

    @Test
    public void 주기변경_10분_명령어_등록_테스트() {
    }

    @Test
    public void 주기변경_10분_검증_테스트() {
    }

    @Test
    public void 주기변경_60분_검증_테스트() {
    }

    @Test
    public void 누수점검이_상태가_R_인_수용가리스트_조회_테스트() {
    }

    @Test
    public void 상태가_R_인_수용가의_단말기_10분_주기변경여부확인_테스트() {
    }

    @Test
    public void 일부_단말기의_10분_주기변경실패시_로직_테스트() {
    }

    @Test
    public void 점검대상_단말기_주기변경_완료후_누수점검_시작_테스트() {
    }

    @Test
    public void 누수점검_종료일이_도래한_수용가리스트_조회_및누수점검_종료_테스트() {
    }

    @Test
    public void 주기변경_60분_명령어_등록_테스트() {
    }

    @Test
    public void 일부_단말기의_60분_주기변경실패시_로직_테스트() {
    }

    @Test
    public void 점검이완료된_수용가들의_60분_주기변경여부확인_테스트() {
    }

    @Test
    public void 검침데이터_10분단위_를바탕으로_사용량을_계산_테스트() {
    }
}