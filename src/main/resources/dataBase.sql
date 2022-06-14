Create table Students
(
    student_id Integer           NOT NULL,
    id         Integer Default 0 NOT NULL UNIQUE,
    surname    Varchar2 (20) NOT NULL,
    name       Varchar2 (20) NOT NULL,
    patronymic Varchar2 (30),
    primary key (student_id)
)/
Create table Teacher
(
    teacher_id Integer           NOT NULL,
    id         Integer Default 0 NOT NULL UNIQUE,
    surname    Varchar2 (20) NOT NULL,
    name       Varchar2 (20) NOT NULL,
    salary     Integer,
    patronymic Varchar2 (30),
    primary key (teacher_id)
)
/
Create table Subject
(
    subject_id   Integer Default -1 NOT NULL,
    subject_name Varchar2 (20) NOT NULL,
    semester     Varchar2 (30) NOT NULL,
    max_grade    Integer,
    primary key (subject_id)
)
/
Create table Task
(
    task_name  Varchar2 (30) NOT NULL UNIQUE,
    subject_id Integer Default -1 NOT NULL,
    subject    Varchar2 (50) NOT NULL,
    max_grade  Integer,
    primary key (task_name, subject_id)
)
/
Create table Report
(
    report_id  Integer NOT NULL UNIQUE,
    solution   Varchar2 (50) NOT NULL,
    send_date  Date    NOT NULL,
    student_id Integer NOT NULL,
    task_name  Varchar2 (30) NOT NULL,
    primary key (report_id, student_id, task_name)
)
/
Create table Review
(
    review_id   Integer NOT NULL UNIQUE,
    teacher_id  Integer NOT NULL,
    report_id   Integer NOT NULL,
    grade       Integer NOT NULL,
    time_review Date    NOT NULL,
    primary key (review_id, teacher_id, report_id)
)/

Create table Connecting_Student
(
    id         Integer            NOT NULL,
    student_id Integer            NOT NULL,
    subject_id Integer Default -1 NOT NULL,
    primary key (id, student_id, subject_id)
)/

Create table Connecting_Teacher
(
    id         Integer            NOT NULL,
    teacher_id Integer            NOT NULL,
    subject_id Integer Default -1 NOT NULL,
    primary key (id, teacher_id, subject_id)
)/

Create table Admin
(
    admin_id   Integer           NOT NULL,
    id         Integer Default 0 NOT NULL UNIQUE,
    name       Varchar2 (30) NOT NULL,
    surname    Varchar2 (30) NOT NULL,
    patronymic Varchar2 (30),
    primary key (admin_id)
)/

Create table Iogin_info
(
    id       Integer Default 0 NOT NULL,
    password Varchar2 (500) NOT NULL,
    username Varchar2 (30) NOT NULL,
    role     Varchar2 (30) NOT NULL,
    primary key (id)
)/

Alter table Report
    add foreign key (student_id) references Students (student_id) on delete cascade/

Alter table Connecting_Student
    add foreign key (student_id) references Students (student_id) on delete cascade/

Alter table Review
    add foreign key (teacher_id) references Teacher (teacher_id) on delete set null/

Alter table Connecting_Teacher
    add foreign key (teacher_id) references Teacher (teacher_id) on delete cascade/

Alter table Connecting_Student
    add foreign key (subject_id) references Subject (subject_id) on delete cascade/

Alter table Connecting_Teacher
    add foreign key (subject_id) references Subject (subject_id) on delete cascade/

Alter table Task
    add foreign key (subject_id) references Subject (subject_id) on delete cascade/

Alter table Report
    add foreign key (task_name) references Task (task_name) on delete cascade/

Alter table Review
    add foreign key (report_id) references Report (report_id) on delete cascade/
-------------------------------
create table EVENT_LOG
(
    ID         int generated as identity,
    DATE_TIME  timestamp not null,
    TABLE_NAME VARCHAR2(50),
    EVENT_TYPE VARCHAR2(50),
    OBJECT_ID  VARCHAR2(50),
    AUTHOR     VARCHAR2(50)
)
/
BEGIN DBMS_SCHEDULER.CREATE_JOB(
    JOB_NAME => 'Audit_job',
    JOB_TYPE => 'PLSQL_BLOCK',
    JOB_ACTION => 'TRUNCATE TABLE EVENT_LOG;',
    START_DATE => TO_DATe('05-06-2022'),
    REPEAT_INTERVAL => 'FREQ=MONTHLY; BYMONTHDAY=1',
    END_DATE => TO_DATe('05-06-2032'),
    COMMENTS => 'event table cleanup',
    ENABLED => TRUE);
END;
/


CREATE OR REPLACE TRIGGER STUDENT_AUDIT
    AFTER INSERT OR UPDATE OR DELETE
ON STUDENTS
    FOR EACH ROW
DECLARE
v_username varchar2(50);
BEGIN
SELECT user
INTO v_username
FROM DUAL;
CASE
        WHEN INSERTING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'STUDENTS', 'INSERT', :new.STUDENT_ID, v_username);
WHEN UPDATING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'STUDENTS', 'UPDATE', :new.STUDENT_ID, v_username);
WHEN DELETING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'STUDENTS', 'DELETE', :old.STUDENT_ID, v_username);
END
CASE;
END;
/

CREATE OR REPLACE TRIGGER TEACHER_AUDIT
    AFTER INSERT OR UPDATE OR DELETE
ON TEACHER
    FOR EACH ROW
DECLARE
v_username varchar2(50);
BEGIN
SELECT user
INTO v_username
FROM DUAL;
CASE
        WHEN INSERTING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'TEACHER', 'INSERT', :new.TEACHER_ID, v_username);
WHEN UPDATING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'TEACHER', 'UPDATE', :new.TEACHER_ID, v_username);
WHEN DELETING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'TEACHER', 'DELETE', :old.TEACHER_ID, v_username);
END
CASE;
END;
/
CREATE OR  REPLACE TRIGGER ADMIN_AUDIT
    AFTER INSERT OR UPDATE OR DELETE
ON ADMIN
    FOR EACH ROW
DECLARE
v_username varchar2(50);
BEGIN
SELECT user
INTO v_username
FROM DUAL;
CASE
        WHEN INSERTING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'ADMIN', 'INSERT', :new.ADMIN_ID, v_username);
WHEN UPDATING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'ADMIN', 'UPDATE', :new.ADMIN_ID, v_username);
WHEN DELETING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'ADMIN', 'DELETE', :old.ADMIN_ID, v_username);
END
CASE;
END;
/
CREATE OR REPLACE TRIGGER SUBJECT_AUDIT
    AFTER INSERT OR UPDATE OR DELETE
ON SUBJECT
    FOR EACH ROW
DECLARE
v_username varchar2(50);
BEGIN
SELECT user
INTO v_username
FROM DUAL;
CASE
        WHEN INSERTING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'INSERT', :new.SUBJECT_ID, v_username);
WHEN UPDATING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'UPDATE', :new.SUBJECT_ID, v_username);
WHEN DELETING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'DELETE', :old.SUBJECT_ID, v_username);
END
CASE;
END;
/
CREATE OR REPLACE TRIGGER LOGIN_INFO_AUDIT
    AFTER INSERT OR UPDATE OR DELETE
ON IOGIN_INFO
    FOR EACH ROW
DECLARE
v_username varchar2(50);
BEGIN
SELECT user
INTO v_username
FROM DUAL;
CASE
        WHEN INSERTING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'INSERT', :new.ID, v_username);
WHEN UPDATING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'UPDATE', :new.ID, v_username);
WHEN DELETING THEN
            INSERT INTO EVENT_LOG (DATE_TIME, TABLE_NAME, EVENT_TYPE, OBJECT_ID, AUTHOR)
            VALUES (CURRENT_TIMESTAMP, 'SUBJECT', 'DELETE', :old.ID, v_username);
END
CASE;
END;
/
-------------------------------
INSERT INTO Iogin_info (id, password, username, role)
VALUES (1, '$2a$12$Kvc6ZRhW.fZYTc4w9mRIA.yCjVGUs0ie.jgm4K.16Ktl.AktqWf.m', 'Student', 'STUDENT')/
INSERT INTO Iogin_info (id, password, username, role)
VALUES (2, '$2a$12$9CskWp6kiASko4rai6CuO.X8inijH.bv5g2IGRU6MqqCVIPgFb58O', 'Admin', 'ADMIN')/
INSERT INTO Iogin_info (id, password, username, role)
VALUES (3, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'Teacher', 'TEACHER')/
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC)
VALUES ('0', '1', 'SAVOSTIAN', 'VITALIY', 'V')/
INSERT INTO TEACHER (teacher_id, id, surname, name, salary, patronymic)
VALUES (0, 3, 'KOVAL', 'VITALIY', 5000, 'V')/
INSERT INTO Admin (admin_id, id, name, surname)
VALUES (0, 2, 'PETRO', 'STEPANENCO')/

