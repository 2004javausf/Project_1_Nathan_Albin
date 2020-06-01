CREATE TABLE TRS_USERS(
USERNAME VARCHAR2(50) PRIMARY KEY,
PASSWORD VARCHAR2(50),
FIRST_NAME VARCHAR2(50),
LAST_NAME VARCHAR2(50),
BALANCE NUMBER(*,2),
ACCOUNT_TYPE VARCHAR2(50));

CREATE TABLE FORMS(
F_ID INTEGER PRIMARY KEY,
USERNAME VARCHAR2(50),
E_Type VARCHAR2(50),
E_COST NUMBER (*,2),
E_GRADE_TYPE VARCHAR2(50),
E_STARTD DATE,
R_AMT NUMBER(*,2),
R_ADDITIONAL NUMBER (*,2),
F_GRADE VARCHAR2(50),
F_STATUS VARCHAR2(50)
);

--FORM STATUSES--
    --Not Accepted
    --Not Accepted (Urgent)
    --Processing
    --Awarded
    --Not Awarded
    --Voided

CREATE TABLE PROCESSING(
USERNAME VARCHAR2(50),
F_ID INTEGER,
DIR_SUP_APPROVE VARCHAR2(50),
DEPT_HEAD_APPROVE VARCHAR2(50),
BENCO_APPROVE VARCHAR2(50),
DIR_SUP_REASON VARCHAR2(50),
DEPT_HEAD_REASON VARCHAR2(50),
BENCO_REASON VARCHAR2(50)
);

CREATE TABLE NOTIF(
USERNAME VARCHAR2(50),
F_ID INTEGER,
CHANGE_AMT NUMBER(*,2),
NEW_BALANCE NUMBER(*,2),
NOTIF_STATUS VARCHAR2(50)
);

--FOREIGN KEYS--
ALTER TABLE NOTIF
ADD CONSTRAINT FK_NOTIF_TRS_USERS
FOREIGN KEY(USERNAME) REFERENCES TRS_USERS(USERNAME);

ALTER TABLE NOTIF
ADD CONSTRAINT FK_NOTIF_FORMS
FOREIGN KEY(F_ID) REFERENCES FORMS(F_ID);

ALTER TABLE PROCESSING
ADD CONSTRAINT FK_PROCESSING_TRS_USERS
FOREIGN KEY(USERNAME) REFERENCES TRS_USERS(USERNAME);

ALTER TABLE PROCESSING
ADD CONSTRAINT FK_PROCESSING_FORMS
FOREIGN KEY(F_ID) REFERENCES FORMS(F_ID);

ALTER TABLE FORMS
ADD CONSTRAINT FK_FORMS_TRS_USERS
FOREIGN KEY(USERNAME) REFERENCES TRS_USERS(USERNAME);

--SEQUENCES--
DROP SEQUENCE SEQ_F_ID;
DROP SEQUENCE SEQ_F_ID2;
CREATE SEQUENCE SEQ_F_ID;
CREATE SEQUENCE SEQ_F_ID2;

--PROCEDURES--
CREATE OR REPLACE PROCEDURE CHANGE_REIMBURSEMENT 
(RAMT IN NUMBER, FID IN INTEGER, BALL IN NUMBER, USR IN VARCHAR2)
AS
BEGIN
UPDATE FORMS SET R_AMT = RAMT WHERE F_ID = FID;
UPDATE TRS_USERS SET BALANCE = BALL WHERE USERNAME = USR;
INSERT INTO NOTIF VALUES(USR, FID, RAMT, BALL, 'Not Accepted');
COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE ACCDENY_NOTIF
(FID IN INTEGER, BALL IN NUMBER, USR IN VARCHAR2)
AS
BEGIN
UPDATE NOTIF SET NOTIF_STATUS = 'Deny' WHERE F_ID = FID;
UPDATE FORMS SET F_STATUS = 'Voided' WHERE F_ID = FID;
UPDATE TRS_USERS SET BALANCE = BALL WHERE USERNAME = USR;
COMMIT;
END;
/




