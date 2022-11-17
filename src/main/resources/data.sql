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


/* 10분 주기 데이터 */
/* 864447051283958 */
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 9:26','2022-11-17 9:26','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','188.138','16.9','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 9:16','2022-11-17 9:16','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.958','18.3','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 9:06','2022-11-17 9:06','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.5','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:56','2022-11-17 8:56','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.5','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:46','2022-11-17 8:46','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.5','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:36','2022-11-17 8:36','-76','0','3357','-820','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.5','2022-11-17 9:26','2022-11-17 9:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:26','2022-11-17 8:26','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.2','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:16','2022-11-17 8:16','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.929','18.5','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 8:06','2022-11-17 8:06','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.899','18.6','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:56','2022-11-17 7:56','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.899','18.5','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:46','2022-11-17 7:46','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.889','18.5','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:36','2022-11-17 7:36','-76','0','3357','-830','-100','-150','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.879','18.5','2022-11-17 3:26','2022-11-17 3:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:26','2022-11-17 7:26','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.856','17.4','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:16','2022-11-17 7:16','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.821','18.5','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 7:06','2022-11-17 7:06','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.718','18.6','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 6:56','2022-11-17 6:56','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.502','19.1','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 6:46','2022-11-17 6:46','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.498','19.1','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 6:36','2022-11-17 6:36','-77','0','3357','-830','-100','-170','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.498','19','2022-11-16 21:26','2022-11-16 21:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 6:26','2022-11-17 6:26','-76','0','3357','-820','-100','-160','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.481','18.8','2022-11-16 15:26','2022-11-16 15:26');
INSERT INTO meterdataseoulnbiot (imei, imsi, metering_date, receiving_date, rssi, ber, cid, rsrp, rsrq, snr, modem_serial, modem_fwversion, modem_battery, meter_serial, meter_type, meter_size, meter_status_overload, meter_status_leaked, meter_status_reversed, meter_status_battery, metering_interval, reporting_interval, metering_value, modem_temp, insert_date, update_date) VALUES ('864447051283958','450061236858679','2022-11-17 6:16','2022-11-17 6:16','-76','0','3357','-820','-100','-160','2207007329','2.3','3.6','21513453','1','20','0','0','0','0','1','6','187.444','18.9','2022-11-16 15:26','2022-11-16 15:26');


/*
#############
# WAPI #
#############
*/
create table mtd_nb_water_leak_exam_group
(
    exam_group_idx bigint not null primary key,
    created_date datetime null,
    created_user varchar(20) null,
    exam_finished_dt datetime null,
    exam_nm varchar(200) null,
    exam_plan_start_dt datetime null,
    exam_started_dt datetime null,
    exam_status varchar(10) null,
    group_sid bigint null
);

create table mtd_nb_water_leak_exam_wateruser
(
    exam_wateruser_idx bigint not null primary key,
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
    constraint FK7k2x56vl5jcx2ug72a0dgpi9w foreign key (exam_wateruser_idx) references mtd_nb_water_leak_exam_wateruser (exam_wateruser_idx)
);


