/*
#############
# REPORTING #
#############
*/
create table ack_nbiot
(
    imei           varchar(50)                        not null primary key,
    nb_instruction varchar(50)                        not null,
    insert_date    datetime default CURRENT_TIMESTAMP not null,
    update_date    datetime null
);

INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712345', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');
INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712346', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');
INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712347', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');


create table meterdataseoulnbiot
(
    imei varchar(50) not null comment '이동통신ID IMEI',
    imsi varchar(50) not null comment '이동통신ID IMSI',
    metering_date datetime not null comment '미터링시각',
    receiving_date datetime not null comment '수신시각',
    rssi int default 0 not null comment '무선품질 RSSI',
    ber int default 0 not null comment '무선품질 BER',
    cid int default 0 not null comment '무선품질 CID',
    rsrp int default 0 not null comment '무선품질 RSRP',
    rsrq int default 0 not null comment '무선품질 RSRQ',
    snr int default 0 not null comment '무선품질 SNR',
    modem_serial varchar(50) null comment '단말기_일련번호',
    modem_fwversion varchar(50) null comment '단말기_F/W 버전',
    modem_battery decimal(10,3) null comment '단말기_배터리 전압',
    meter_serial varchar(50) null comment '계량기 기물번호',
    meter_type varchar(50) null comment '계량기 형식',
    meter_size varchar(50) null comment '계량기 구경',
    meter_status_overload int default 0 not null comment '과부하검출여부 TRUE:1, FALSE:0',
    meter_status_leaked int default 0 not null comment '옥내누수여부 TRUE:1, FALSE:0',
    meter_status_reversed int default 0 not null comment '역류여부 TRUE:1, FALSE:0',
    meter_status_battery int default 0 not null comment '배터리 1:저전압, 0:정상',
    metering_interval int default 0 not null comment '검침주기',
    reporting_interval int default 0 not null comment '보고주기',
    metering_value decimal(10,3) default -99.000 not null comment '수신지침',
    modem_temp decimal(10,3) default -99.000 not null comment '모뎀온도',
    insert_date datetime default CURRENT_TIMESTAMP not null,
    update_date datetime null,
    primary key (imei, metering_date)
) comment '서울미터데이터NBIOT';
create index meterdataseoulnbiot_imei_IDX on meterdataseoulnbiot (imei);
create index meterdataseoulnbiot_metering_date_IDX on meterdataseoulnbiot (metering_date);
create index meterdataseoulnbiot_receiving_date_IDX on meterdataseoulnbiot (receiving_date);

INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040747271', '450061236539258', '2022-11-16 15:58:35', '2022-11-16 15:58:53', -74, 0, 14870, -810, -100, -120, '2204009150', '2.3', 3.500, '21523634', '01', '20', 0, 0, 0, 0, 1, 6, 81.270, 9.300, '2022-11-16 15:58:53', '2022-11-16 15:58:56');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447050649316', '450061236858107', '2022-11-16 15:58:34', '2022-11-16 15:58:52', -87, 0, 17174, -940, -110, -90, '2207005757', '2.3', 3.600, '21515531', '01', '20', 0, 0, 0, 0, 1, 6, 99.934, 21.900, '2022-11-16 15:58:52', '2022-11-16 15:58:55');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447050951175', '450061239147799', '2022-11-16 15:58:33', '2022-11-16 15:58:50', -65, 0, 61988, -710, -100, -240, '2209001856', '2.4', 3.600, '22510128', '01', '20', 0, 0, 1, 0, 1, 6, 107.500, 12.200, '2022-11-16 15:58:50', '2022-11-16 15:58:54');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447050224516', '450061236789249', '2022-11-16 15:58:32', '2022-11-16 15:58:55', -76, 0, 15898, -820, -100, -130, '2207004408', '2.3', 3.600, '21515565', '01', '20', 0, 0, 0, 0, 1, 6, 33.712, 20.800, '2022-11-16 15:58:55', '2022-11-16 15:58:58');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('866416048185418', '450061236537788', '2022-11-16 15:58:31', '2022-11-16 15:58:47', -68, 0, 5150, -740, -100, -290, '2204007756', '2.3', 3.500, '21524203', '01', '25', 0, 0, 0, 0, 1, 6, 829.563, 14.400, '2022-11-16 15:58:47', '2022-11-16 15:58:49');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447052775275', '450061236200684', '2022-11-16 15:58:30', '2022-11-16 15:58:50', -95, 0, 7190, -1050, -130, -20, '2112000685', '2.3', 3.600, '21517896', '01', '20', 0, 0, 0, 0, 1, 6, 0.424, 12.200, '2022-11-16 15:58:50', '2022-11-16 15:58:54');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700041406596', '450061235457977', '2022-11-16 15:58:27', '2022-11-16 15:58:53', -101, 0, 13082, -1090, -110, -90, 'FFFFFFFFFF', '1.4', 3.600, '22530009', '01', '80', 0, 0, 0, 0, 1, 6, 497.740, 14.400, '2022-11-16 15:58:53', '2022-11-16 15:58:56');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051282497', '450061236858801', '2022-11-16 15:58:26', '2022-11-16 15:58:42', -63, 0, 12825, -690, -100, -200, '2207007451', '2.3', 3.600, '21514973', '01', '20', 0, 0, 0, 0, 1, 6, 0.322, 14.500, '2022-11-16 15:58:42', '2022-11-16 15:58:45');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051333704', '450061239146195', '2022-11-16 15:58:23', '2022-11-16 15:58:40', -86, 0, 537, -1000, -130, 0, '2208004491', '2.4', 3.600, '22510665', '01', '25', 0, 0, 1, 0, 1, 6, 16.735, 12.400, '2022-11-16 15:58:40', '2022-11-16 15:58:43');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700048660229', '450061236770132', '2022-11-16 15:58:22', '2022-11-16 15:58:40', -76, 0, 13850, -820, -100, -220, '2206004133', '2.3', 3.600, '21515934', '01', '20', 0, 0, 0, 0, 1, 6, 25.234, 20.800, '2022-11-16 15:58:40', '2022-11-16 15:58:43');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744005', '450061236539192', '2022-11-16 15:58:18', '2022-11-16 15:58:33', -78, 0, 9250, -840, -100, -240, '2204009084', '2.4', 3.600, '21523189', '01', '20', 0, 0, 0, 0, 1, 6, 20.671, 14.500, '2022-11-16 15:58:33', '2022-11-16 15:58:35');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('866416048271929', '450061236537666', '2022-11-16 15:58:17', '2022-11-16 15:58:42', -91, 0, 15127, -1030, -140, -10, '2204007634', '2.3', 3.600, '21521934', '01', '20', 0, 0, 0, 0, 1, 6, 17.297, 14.500, '2022-11-16 15:58:42', '2022-11-16 15:58:44');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('866416048237276', '450061236537300', '2022-11-16 15:58:17', '2022-11-16 15:58:34', -83, 0, 5150, -890, -100, -190, '2204007268', '2.3', 3.600, '21512244', '01', '20', 0, 0, 0, 0, 1, 6, 257.692, 12.000, '2022-11-16 15:58:34', '2022-11-16 15:58:37');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700043151927', '450061235949503', '2022-11-16 15:58:16', '2022-11-16 15:58:39', -85, 0, 36381, -910, -100, -150, 'FFFFFFFFFF', '1.4', 3.500, '21518411', '01', '20', 0, 0, 0, 0, 1, 6, 88.053, 10.700, '2022-11-16 15:58:39', '2022-11-16 15:58:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040628877', '450061236538897', '2022-11-16 15:58:08', '2022-11-16 15:58:18', -82, 0, 12827, -920, -100, -170, '2204008789', '2.3', 3.500, '21523513', '01', '20', 0, 0, 0, 0, 1, 6, 34.305, 7.500, '2022-11-16 15:58:18', '2022-11-16 15:58:21');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700043032283', '450061222718251', '2022-11-16 15:58:07', '2022-11-16 15:58:31', -93, 0, 4131, -1000, -100, -170, 'FFFFFFFFFF', '1.4', 3.500, '21510610', '01', '20', 0, 0, 0, 0, 1, 6, 187.136, 11.700, '2022-11-16 15:58:31', '2022-11-16 15:58:33');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('866416048174776', '450061236537230', '2022-11-16 15:58:03', '2022-11-16 15:58:22', -77, 0, 13849, -830, -100, -240, '2204007198', '2.3', 3.600, '21512389', '01', '20', 0, 0, 0, 0, 1, 6, 141.083, 12.400, '2022-11-16 15:58:22', '2022-11-16 15:58:25');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700043274430', '450061235948870', '2022-11-16 15:58:03', '2022-11-16 15:58:29', -71, 0, 12317, -770, -100, -300, 'FFFFFFFFFF', '1.4', 3.500, '21510059', '01', '20', 0, 1, 0, 0, 1, 6, 418.353, 14.200, '2022-11-16 15:58:29', '2022-11-16 15:58:31');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447053909600', '450061236142161', '2022-11-16 15:58:03', '2022-11-16 15:58:22', -65, 0, 12316, -720, -100, -110, '2112001592', '2.3', 3.600, '21524003', '01', '25', 0, 0, 0, 0, 1, 6, 537.589, 12.000, '2022-11-16 15:58:22', '2022-11-16 15:58:25');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051066247', '450061236859678', '2022-11-16 15:58:03', '2022-11-16 15:58:22', -81, 0, 11044, -870, -100, -290, '2207008328', '2.3', 3.600, '21513177', '01', '20', 0, 0, 0, 0, 1, 6, 115.615, 12.600, '2022-11-16 15:58:22', '2022-11-16 15:58:26');



/* ############################################################################################################################################################ */
/* ############################################################################################################################################################ */
/* ############################################################################################################################################################ */
/* ############################################################################################################################################################ */
/* ############################################################################################################################################################ */

/*
#############
#### WAPI ###
#############
*/
create table mtd_nb_water_leak_exam_group
(
    exam_group_idx     bigint       not null primary key,
    created_date       datetime     null,
    created_user       varchar(20)  null,
    exam_finished_dt   datetime     null,
    exam_nm            varchar(200) null,
    exam_plan_start_dt datetime     null,
    exam_started_dt    datetime     null,
    exam_status        varchar(10)  null,
    group_sid          bigint       null
);
INSERT INTO mtd_nb_water_leak_exam_group (exam_group_idx, created_date, created_user, exam_finished_dt, exam_nm, exam_plan_start_dt, exam_started_dt, exam_status, group_sid) VALUES (1, '2022-11-16 14:02:33', '관리자', '2022-11-16 14:02:44', '등록', '2022-11-16 14:02:44', '2022-11-16 14:02:44', 'F', 6);


create table mtd_nb_water_leak_exam_wateruser
(
    exam_wateruser_idx bigint not null
        primary key,
    consumer_sid bigint null,
    exam_result varchar(5) null,
    imei varchar(50) null,
    leak_min_usage decimal(10,3) null,
    exam_group_idx bigint null,
    constraint UK_9b3fs5xe8aw5d01ui8ks8k0f3 unique (imei),
    constraint UK_agxmpte0ncic5u5rl134r9qbc unique (consumer_sid),
    constraint FK9w5vrl0a7tmfeyajm176y563r foreign key (exam_group_idx) references mtd_nb_water_leak_exam_group (exam_group_idx)
);


create table mtd_nb_meterinfo_leak
(
    id bigint not null
        primary key,
    acube_signal01 int null,
    acube_signal02 int null,
    criterion_date datetime null,
    group_sid bigint null,
    info_tag varchar(25) null,
    metering_date datetime null,
    metering_signal01 int null,
    metering_signal02 int null,
    metering_signal03 int null,
    metering_signal04 int null,
    metering_state varchar(50) null,
    metering_usage decimal(10,3) null,
    metering_value decimal(10,3) null,
    modem_id varchar(50) null,
    modem_rssi int null,
    modem_signal01 int null,
    modem_signal02 int null,
    modem_signal03 int null,
    receiving_date datetime null,
    exam_wateruser_idx bigint null,
    constraint FK7k2x56vl5jcx2ug72a0dgpi9w
        foreign key (exam_wateruser_idx) references mtd_nb_water_leak_exam_wateruser (exam_wateruser_idx)
);
