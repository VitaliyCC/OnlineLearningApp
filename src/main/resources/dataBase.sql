Create table Students (
                          student_id Integer NOT NULL ,
                          id Integer Default 0 NOT NULL  UNIQUE ,
                          surname Varchar2 (20) NOT NULL ,
                          name Varchar2 (20) NOT NULL ,
                          patronymic Varchar2 (30),
                          primary key (student_id)
);

Create table Teacher (
                         teacher_id Integer NOT NULL ,
                         id Integer Default 0 NOT NULL  UNIQUE ,
                         surname Varchar2 (20) NOT NULL ,
                         name Varchar2 (20) NOT NULL ,
                         salary Integer,
                         patronymic Varchar2 (30),
                         primary key (teacher_id)
);

Create table Subject (
                         subject_id Integer Default -1 NOT NULL ,
                         subject_name Varchar2 (20) NOT NULL  UNIQUE ,
                         semester Varchar2 (30) NOT NULL ,
                         max_grade Integer,
                         primary key (subject_id)
);

Create table Task (
                      task_name Varchar2 (30) NOT NULL  UNIQUE ,
                      subject_id Integer Default -1 NOT NULL ,
                      subject Varchar2 (50) NOT NULL ,
                      max_grade Integer,
                      primary key (task_name,subject_id)
);

Create table Report (
                        report_id Integer NOT NULL  UNIQUE ,
                        solution Varchar2 (50) NOT NULL ,
                        send_date Date NOT NULL ,
                        student_id Integer NOT NULL ,
                        task_name Varchar2 (30) NOT NULL ,
                        primary key (report_id,student_id,task_name)
);

Create table Review (
                        review_id Integer NOT NULL  UNIQUE ,
                        teacher_id Integer NOT NULL ,
                        report_id Integer NOT NULL ,
                        grade Integer NOT NULL ,
                        time_review Date NOT NULL ,
                        primary key (review_id,teacher_id,report_id)
);

Create table Connecting_Student (
                                    id Integer NOT NULL ,
                                    student_id Integer NOT NULL ,
                                    subject_id Integer Default -1 NOT NULL ,
                                    primary key (id,student_id,subject_id)
);

Create table Connecting_Teacher (
                                    id Integer NOT NULL ,
                                    teacher_id Integer NOT NULL ,
                                    subject_id Integer Default -1 NOT NULL ,
                                    primary key (id,teacher_id,subject_id)
);

Create table Admin (
                       admin_id Integer NOT NULL ,
                       id Integer Default 0 NOT NULL  UNIQUE ,
                       name Varchar2 (30) NOT NULL ,
                       surname Varchar2 (30) NOT NULL ,
                       patronymic Varchar2 (30),
                       primary key (admin_id)
);

Create table Iogin_info (
                            id Integer Default 0 NOT NULL ,
                            password Varchar2 (500) NOT NULL ,
                            username Varchar2 (30) NOT NULL  UNIQUE ,
                            role Varchar2 (30) NOT NULL ,
                            primary key (id)
);

Alter table Report add  foreign key (student_id) references Students (student_id)  on delete cascade;

Alter table Connecting_Student add  foreign key (student_id) references Students (student_id)  on delete cascade;

Alter table Review add  foreign key (teacher_id) references Teacher (teacher_id)  on delete set null;

Alter table Connecting_Teacher add  foreign key (teacher_id) references Teacher (teacher_id)  on delete cascade;

Alter table Connecting_Student add  foreign key (subject_id) references Subject (subject_id)  on delete cascade;

Alter table Connecting_Teacher add  foreign key (subject_id) references Subject (subject_id)  on delete cascade;

Alter table Task add  foreign key (subject_id) references Subject (subject_id)  on delete cascade;

Alter table Report add  foreign key (task_name) references Task (task_name)  on delete cascade;

Alter table Review add  foreign key (report_id) references Report (report_id)  on delete cascade;
-------------------------------
INSERT INTO Iogin_info (id, password, username, role)
VALUES (1, '$2a$12$Kvc6ZRhW.fZYTc4w9mRIA.yCjVGUs0ie.jgm4K.16Ktl.AktqWf.m', 'Student', 'STUDENT');
INSERT INTO Iogin_info (id, password, username, role)
VALUES (2, '$2a$12$9CskWp6kiASko4rai6CuO.X8inijH.bv5g2IGRU6MqqCVIPgFb58O', 'Admin', 'ADMIN');
INSERT INTO Iogin_info (id, password, username, role)
VALUES (3, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'Teacher', 'TEACHER');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC)
VALUES ('0', '1', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO TEACHER (teacher_id, id, surname, name, salary, patronymic)
VALUES (0, 3, 'KOVAL', 'VITALIY', 5000, 'V');
INSERT INTO Admin (admin_id, id, name, surname)
VALUES (0, 2, 'PETRO', 'STEPANENCO');
INSERT INTO SUBJECT (subject_id, subject_name, semester, max_grade)
VALUES (0, 'ACADEMIC INTEGRITY', 4, 100);
INSERT INTO Connecting_Teacher (id, teacher_id, subject_id)
VALUES (0, 0, 0);
INSERT INTO Connecting_Student (id, student_id, subject_id)
VALUES (0, 0, 0);
INSERT INTO TASK (TASK_NAME, SUBJECT_ID, SUBJECT, MAX_GRADE)
VALUES ('FirstLab', 0, 'https://mix.sumdu.edu.ua/2_ERD.pdf', '10');
INSERT INTO TASK (TASK_NAME, SUBJECT_ID, SUBJECT, MAX_GRADE)
VALUES ('SecondLab', 0, 'https://mix.sumdu.edu.ua/2_ERD.pdf', '10');
INSERT INTO Report (REPORT_ID, SOLUTION, SEND_DATE, STUDENT_ID, TASK_NAME)
VALUES (0, 'https://mix.sumdu.edu.ua/index.html1', ' 19-02-22', 0, 'FirstLab');
INSERT INTO Report (REPORT_ID, SOLUTION, SEND_DATE, STUDENT_ID, TASK_NAME)
VALUES (1, 'https://mix.sumdu.edu.ua/index.html1', ' 19-02-25', 0, 'FirstLab');
INSERT INTO Report (REPORT_ID, SOLUTION, SEND_DATE, STUDENT_ID, TASK_NAME)
VALUES (3, 'https://mix.sumdu.edu.ua/index.html1', ' 19-02-26', 0, 'SecondLab');
INSERT INTO Review (review_id, teacher_id, report_id, grade, time_review)
VALUES (0, 0, 0, 6, ' 25-02-22');
INSERT INTO Review (review_id, teacher_id, report_id, grade, time_review)
VALUES (1, 0, 1, 10, ' 25-02-25');
INSERT INTO Review (review_id, teacher_id, report_id, grade, time_review)
VALUES (2, 0, 3, 6, ' 25-02-28');
------------------------------------------
Commit;
--------------------------------------------
