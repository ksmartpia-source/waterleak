package com.waterleak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.waterleak.WaterLeak;
import com.waterleak.config.Globals;
import com.waterleak.utils.WaterLeakChangeVerification;
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
public class WaterLeakChangeCycleTest {
  @Autowired
  private WaterLeakChangeVerification changeVerification;

  @Test
  @Transactional
  public void 주기변경_10분_검증_테스트() {
    //given
    String tenMinIMEI = "864447051283958";
    //when
    Boolean result = changeVerification.isCycleChangeVerification(tenMinIMEI, Globals.CYCLE_10_MIN);
    //then
    assertEquals(true, result);
  }

  @Test
  @Transactional
  public void 주기변경_10분_검증_테스트_실패() {
    //given
    String tenMinIMEI = "864447058887111";
    //when
    Boolean result = changeVerification.isCycleChangeVerification(tenMinIMEI, Globals.CYCLE_10_MIN);
    //then
    assertEquals(false, result);
  }

  @Test
  @Transactional
  public void 주기변경_60분_검증_테스트() {
    //given
    String tenMinIMEI = "864700040744484";
    //when
    Boolean result = changeVerification.isCycleChangeVerification(tenMinIMEI, Globals.CYCLE_60_MIN);
    //then
    assertEquals(true, result);
  }

  @Test
  @Transactional
  public void 일부_단말기의_60분_주기변경실패시_로직_테스트() {
  }
}