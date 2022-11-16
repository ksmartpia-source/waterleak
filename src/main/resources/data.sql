create table ack_nbiot
(
    imei           varchar(50)                        not null primary key,
    nb_instruction varchar(50)                        not null,
    insert_date    datetime default CURRENT_TIMESTAMP not null,
    update_date    datetime null
);

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

INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712345', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');
INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712346', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');
INSERT INTO ack_nbiot (imei, nb_instruction, insert_date, update_date) VALUES ('890123456712347', 'QSDF', '2022-11-16 13:28:02', '2022-11-16 13:28:05');

INSERT INTO mtd_nb_water_leak_exam_group (exam_group_idx, created_date, created_user, exam_finished_dt, exam_nm, exam_plan_start_dt, exam_started_dt, exam_status, group_sid) VALUES (1, '2022-11-16 14:02:33', '관리자', '2022-11-16 14:02:44', '등록', '2022-11-16 14:02:44', '2022-11-16 14:02:44', 'F', 6);