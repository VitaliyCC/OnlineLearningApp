


CREATE TABLE Students
(
    student_id SERIAL PRIMARY KEY,
    id         INTEGER NOT NULL UNIQUE DEFAULT 0,
    surname    VARCHAR(20) NOT NULL,
    name       VARCHAR(20) NOT NULL,
    patronymic VARCHAR(30)
);

CREATE TABLE Teacher
(
    teacher_id SERIAL PRIMARY KEY,
    id         INTEGER NOT NULL UNIQUE DEFAULT 0,
    surname    VARCHAR(20) NOT NULL,
    name       VARCHAR(20) NOT NULL,
    salary     INTEGER,
    patronymic VARCHAR(30)
);

CREATE TABLE Subject
(
    subject_id   SERIAL PRIMARY KEY,
    subject_name VARCHAR(20) NOT NULL,
    semester     VARCHAR(30) NOT NULL,
    max_grade    INTEGER
);

CREATE TABLE Task
(
    task_name  VARCHAR(30) PRIMARY KEY,
    subject_id INTEGER NOT NULL REFERENCES Subject(subject_id) ON DELETE CASCADE,
    subject    VARCHAR(50) NOT NULL,
    max_grade  INTEGER
);

CREATE TABLE Report
(
    report_id  SERIAL PRIMARY KEY,
    solution   VARCHAR(50) NOT NULL,
    send_date  DATE NOT NULL,
    student_id INTEGER NOT NULL REFERENCES Students(student_id) ON DELETE CASCADE,
    task_name  VARCHAR(30) NOT NULL REFERENCES Task(task_name) ON DELETE CASCADE
);

CREATE TABLE Review
(
    review_id   SERIAL PRIMARY KEY,
    teacher_id  INTEGER NOT NULL REFERENCES Teacher(teacher_id) ON DELETE SET NULL,
    report_id   INTEGER NOT NULL REFERENCES Report(report_id) ON DELETE CASCADE,
    grade       INTEGER NOT NULL,
    time_review DATE NOT NULL
);

CREATE TABLE Connecting_Student
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL REFERENCES Students(student_id) ON DELETE CASCADE,
    subject_id INTEGER NOT NULL REFERENCES Subject(subject_id) ON DELETE CASCADE
);

CREATE TABLE Connecting_Teacher
(
    id         SERIAL PRIMARY KEY,
    teacher_id INTEGER NOT NULL REFERENCES Teacher(teacher_id) ON DELETE CASCADE,
    subject_id INTEGER NOT NULL REFERENCES Subject(subject_id) ON DELETE CASCADE
);

CREATE TABLE Admin
(
    admin_id SERIAL PRIMARY KEY,
    id       INTEGER NOT NULL UNIQUE DEFAULT 0,
    name     VARCHAR(30) NOT NULL,
    surname  VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30)
);

CREATE TABLE Login_info
(
    id       INTEGER PRIMARY KEY DEFAULT 0,
    password VARCHAR(500) NOT NULL,
    username VARCHAR(30) NOT NULL,
    role     VARCHAR(30) NOT NULL
);

ALTER TABLE Report
    ADD CONSTRAINT fk_report_student FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE;

ALTER TABLE Connecting_Student
    ADD CONSTRAINT fk_connecting_student_student FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE;

ALTER TABLE Review
    ADD CONSTRAINT fk_review_teacher FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE SET NULL;

ALTER TABLE Connecting_Teacher
    ADD CONSTRAINT fk_connecting_teacher_teacher FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE CASCADE;

ALTER TABLE Connecting_Student
    ADD CONSTRAINT fk_connecting_student_subject FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE;

ALTER TABLE Connecting_Teacher
    ADD CONSTRAINT fk_connecting_teacher_subject FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE;

ALTER TABLE Task
    ADD CONSTRAINT fk_task_subject FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE;

ALTER TABLE Report
    ADD CONSTRAINT fk_report_task FOREIGN KEY (task_name) REFERENCES Task(task_name) ON DELETE CASCADE;

ALTER TABLE Review
    ADD CONSTRAINT fk_review_report FOREIGN KEY (report_id) REFERENCES Report(report_id) ON DELETE CASCADE;

CREATE TABLE EVENT_LOG
(
    ID         SERIAL PRIMARY KEY,
    DATE_TIME  TIMESTAMP NOT NULL,
    TABLE_NAME VARCHAR(50),
    EVENT_TYPE VARCHAR(50),
    OBJECT_ID  VARCHAR(50),
    AUTHOR     VARCHAR(50)
);

-- Вставка даних в таблицю Login_info з хешованими паролями
INSERT INTO Login_info (id, password, username, role)
VALUES (1, '$2a$12$Kvc6ZRhW.fZYTc4w9mRIA.yCjVGUs0ie.jgm4K.16Ktl.AktqWf.m', 'Student', 'STUDENT');

INSERT INTO Login_info (id, password, username, role)
VALUES (2, '$2a$12$9CskWp6kiASko4rai6CuO.X8inijH.bv5g2IGRU6MqqCVIPgFb58O', 'Admin', 'ADMIN');

INSERT INTO Login_info (id, password, username, role)
VALUES (3, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'Teacher', 'TEACHER');

-- Вставка даних в таблиці Students, Teacher та Admin
INSERT INTO Students (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC)
VALUES (0, 1, 'SAVOSTIAN', 'VITALIY', 'V');

INSERT INTO Teacher (teacher_id, id, surname, name, salary, patronymic)
VALUES (0, 3, 'KOVAL', 'VITALIY', 5000, 'V');

INSERT INTO Admin (admin_id, id, name, surname)
VALUES (0, 2, 'PETRO', 'STEPANENCO');

