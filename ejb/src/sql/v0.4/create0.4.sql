﻿CREATE TABLE TASKSTATUS 
(
	ID INTEGER PRIMARY KEY,
	NAME VARCHAR(50) UNIQUE NOT NULL	
);

CREATE TABLE TASKPRIORITY
(
	ID INTEGER PRIMARY KEY,
	NAME VARCHAR(50) UNIQUE NOT NULL,
	LEVEL INTEGER UNIQUE NOT NULL,
	CONSTRAINT CHK_LEVEL CHECK (LEVEL > 0)
);

CREATE TABLE EMPLOYEE 
(
	ID INTEGER PRIMARY KEY,
	FIRSTNAME VARCHAR(50) UNIQUE,
	LASTNAME VARCHAR(50) UNIQUE,
	EMAIL VARCHAR(100) UNIQUE,
	PICTUREURL VARCHAR(200) UNIQUE,
	USERNAME VARCHAR(10) UNIQUE NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	ROLE VARCHAR(20) NOT NULL
);

CREATE TABLE TASK
(
	ID INTEGER PRIMARY KEY,
	TITLE VARCHAR(200) NOT NULL,
	NUMBER VARCHAR(20) UNIQUE NOT NULL,
	DESCRIPTION TEXT NOT NULL,
	DATECREATED DATE NOT NULL,	
	DATEFINISHED DATE,
	TASKSTATUSID INTEGER NOT NULL REFERENCES TASKSTATUS,
	TASKPRIORITYID INTEGER NOT NULL REFERENCES TASKPRIORITY,
	ESTIMATION INTEGER,	
	ASSIGNEDEMPLOYEE INTEGER REFERENCES EMPLOYEE	
);

CREATE TABLE COMMENT
(
	ID INTEGER PRIMARY KEY,
	DATE DATE NOT NULL,
	EMPLOYEEID INTEGER REFERENCES EMPLOYEE
);

CREATE TABLE COMMENTED_TASKS
(
	TASKID INTEGER REFERENCES TASK,
	COMMENTID INTEGER REFERENCES COMMENT,
	EMPLOYEEID INTEGER REFERENCES EMPLOYEE,
	PRIMARY KEY (TASKID, COMMENTID)
);

CREATE TABLE PRIMARY_KEY_SEQUENCES
(	
	TABLE_NAME VARCHAR(50) NOT NULL UNIQUE,
	CURRENT_PK_VALUE INTEGER NOT NULL
);

INSERT INTO PRIMARY_KEY_SEQUENCES (TABLE_NAME, CURRENT_PK_VALUE) VALUES ('EMPLOYEE', 0);
INSERT INTO PRIMARY_KEY_SEQUENCES (TABLE_NAME, CURRENT_PK_VALUE) VALUES ('TASK', 0);
INSERT INTO PRIMARY_KEY_SEQUENCES (TABLE_NAME, CURRENT_PK_VALUE) VALUES ('COMMENT', 0);

INSERT INTO TASKSTATUS (ID, NAME) VALUES (1, 'NEW');
INSERT INTO TASKSTATUS (ID, NAME) VALUES (2, 'OPEN');
INSERT INTO TASKSTATUS (ID, NAME) VALUES (3, 'CLOSED');
INSERT INTO TASKSTATUS (ID, NAME) VALUES (4, 'CANCELLED');

INSERT INTO TASKPRIORITY (ID, NAME, LEVEL) VALUES (1, 'HIGH', 	1);
INSERT INTO TASKPRIORITY (ID, NAME, LEVEL) VALUES (2, 'MEDIUM', 2);
INSERT INTO TASKPRIORITY (ID, NAME, LEVEL) VALUES (3, 'LOW',	3);
