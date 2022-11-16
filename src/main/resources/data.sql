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
    imei varchar(50) not null,
    imsi varchar(50) not null,
    metering_date datetime not null,
    receiving_date datetime not null,
    rssi int default 0 not null ,
    ber int default 0 not null ,
    cid int default 0 not null,
    rsrp int default 0 not null,
    rsrq int default 0 not null,
    snr int default 0 not null ,
    modem_serial varchar(50) null,
    modem_fwversion varchar(50) null,
    modem_battery decimal(10,3) null,
    meter_serial varchar(50) null ,
    meter_type varchar(50) null ,
    meter_size varchar(50) null ,
    meter_status_overload int default 0 not null ,
    meter_status_leaked int default 0 not null ,
    meter_status_reversed int default 0 not null,
    meter_status_battery int default 0 not null,
    metering_interval int default 0 not null ,
    reporting_interval int default 0 not null ,
    metering_value decimal(10,3) default -99.000 not null,
    modem_temp decimal(10,3) default -99.000 not null,
    insert_date datetime default CURRENT_TIMESTAMP not null,
    update_date datetime null,
    primary key (imei, metering_date)
);
create index meterdataseoulnbiot_imei_IDX on meterdataseoulnbiot (imei);
create index meterdataseoulnbiot_metering_date_IDX on meterdataseoulnbiot (metering_date);
create index meterdataseoulnbiot_receiving_date_IDX on meterdataseoulnbiot (receiving_date);

/* 60분 주기 데이터 */
/* 864700040744484 */
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 17:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 100.070, 10.000, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 16:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 100.046, 11.300, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 15:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 100.011, 11.100, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 14:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.999, 10.900, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 13:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.999, 10.800, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 12:17:22', '2022-11-16 17:17:40', -86, 0, 21510, -990, -110, -80, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.957, 10.700, '2022-11-16 17:17:40', '2022-11-16 17:17:42');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 11:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.944, 10.100, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 10:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.935, 10.400, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 09:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.901, 10.400, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 08:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.821, 10.300, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 07:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.705, 10.300, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 06:17:22', '2022-11-16 11:17:38', -84, 0, 21510, -990, -110, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.705, 10.500, '2022-11-16 11:17:38', '2022-11-16 11:17:39');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 05:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.705, 10.200, '2022-11-16 05:17:46', '2022-11-16 05:17:47');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 04:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.693, 10.600, '2022-11-16 05:17:46', '2022-11-16 05:17:47');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 03:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.692, 10.800, '2022-11-16 05:17:46', '2022-11-16 05:17:47');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 02:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.692, 11.000, '2022-11-16 05:17:46', '2022-11-16 05:17:47');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 01:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.692, 11.100, '2022-11-16 05:17:46', '2022-11-16 05:17:46');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-16 00:17:22', '2022-11-16 05:17:46', -86, 0, 21510, -1020, -130, -30, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.671, 11.200, '2022-11-16 05:17:46', '2022-11-16 05:17:46');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-15 23:17:22', '2022-11-15 23:17:42', -85, 0, 21510, -1010, -120, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.657, 9.700, '2022-11-15 23:17:42', '2022-11-15 23:17:43');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864700040744484', '450061236539230', '2022-11-15 22:17:22', '2022-11-15 23:17:42', -85, 0, 21510, -1010, -120, -70, '2204009122', '2.3', 3.600, '21523783', '01', '20', 0, 0, 0, 0, 1, 6, 99.626, 11.600, '2022-11-15 23:17:42', '2022-11-15 23:17:43');