package com.waterleak.config;

public class Globals {
    public static final String WATERLEAK_STATUS_READY = "R";
    public static final String WATERLEAK_STATUS_START = "S";
    public static final String WATERLEAK_STATUS_FINISH = "F";

    public static final String WATERLEAK_STATUS_CHANGE_FAIL = "CF";
    public static final String WATERLEAK_STATUS_CHANGE_10 = "10";
    public static final String WATERLEAK_STATUS_CHANGE_60 = "60";

    public static final String WATERLEAK_RESULT_NORMAL = "N";
    public static final String WATERLEAK_RESULT_LEAK = "L";
    public static final String WATERLEAK_RESULT_FINE_LEAK = "FL";

    public static final int CHECK_LIST_SIZE = 9;
    public static final int RESULT_TRUE_COUNT = 4;
    public static final Long CYCLE_10_MIN = 10L;
    public static final Long CYCLE_60_MIN = 60L;

    //누수점검 도출 관련
    public static final int RESULT_UPPER_COUNT = 6;
    public static final int RESULT_MIDDLE_COUNT = 3;

    //단말기에 전송할 누수점검 명령어
    public static final String NB_INSTRUCTION_TO_10 = "ZZZ";
    public static final String NB_INSTRUCTION_TO_60 = "QQQ";

    //REST API 관련 값
    public static final String MESSAGE = "ment";
    public static final String FAIL= "fail";
    public static final String CONTENT= "content";
    public static final String STATUS= "status";
    public static final String SUCCESS_ADD_MODEM_MESSAGE= "success";
}

