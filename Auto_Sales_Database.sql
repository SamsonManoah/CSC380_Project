CREATE TABLE Customer
( Customer_ID NUMBER(4),
  First_Name VARCHAR2(16),
  Last_Name VARCHAR2(16),
  Phone_Number NUMBER(10),
  CONSTRAINT Customer_Customer_ID_pk PRIMARY KEY(Customer_ID));
  
  INSERT INTO CUSTOMER VALUES (1,'John','Brown',3151234567);
  INSERT INTO CUSTOMER VALUES (2,'Mary','Smith',3152345678);
  INSERT INTO CUSTOMER VALUES (3,'Adam','John',3153456789);
  
/*********************************************************/

CREATE TABLE Customer_Address
( Customer_ID NUMBER(4),
  Street VARCHAR2(24),
  City VARCHAR2(16),
  State CHAR(2),
  Zip NUMBER(5),
  CONSTRAINT Customer_Customer_ID_fk FOREIGN KEY(Customer_ID) 
  REFERENCES Customer(Customer_ID) ON DELETE CASCADE,
  CONSTRAINT Customer_Address_ID_pk PRIMARY KEY(Customer_ID));
  
  INSERT INTO CUSTOMER_ADDRESS VALUES (1,'123 James Street','Syracuse','NY',13206);
  INSERT INTO CUSTOMER_ADDRESS VALUES (3,'456 Trump Street','Watertown','NJ',13324);
  INSERT INTO CUSTOMER_ADDRESS VALUES (2,'234 Clinton Street','Utica','NY',13216);

/********************************************************************************/

CREATE TABLE Auto
( VIN_Number VARCHAR2(12),
  Make VARCHAR2(16),
  Year NUMBER(4),
  CONSTRAINT Auto_VIN_pk PRIMARY KEY(VIN_Number));
  
  INSERT INTO AUTO VALUES ('T1234C567891','Toyota',2014);
  INSERT INTO AUTO VALUES ('H2345C678910','Honda',2015);
  INSERT INTO AUTO VALUES ('H3456S789012','Hyndai',2010);

/*******************************************************/
CREATE TABLE Auto_Model
( Model_Number NUMBER(4),
  VIN_Number VARCHAR2(12),
  Model VARCHAR2(16),
  CONSTRAINT Auto_Model_VIN_Number_fk FOREIGN KEY(VIN_Number) 
  REFERENCES Auto(VIN_Number) ON DELETE CASCADE,
  CONSTRAINT Auto_Model_NumberVIN_pk PRIMARY KEY(Model_Number));
  
  INSERT INTO AUTO_MODEL VALUES (1,'T1234C567891','Camry');
  INSERT INTO AUTO_MODEL VALUES (2,'H2345C678910','Civic');
  INSERT INTO AUTO_MODEL VALUES (3,'H3456S789012','Sonata');
  
/**********************************************************/

CREATE TABLE Auto_Color
( Color_Section VARCHAR2(16),
  VIN_Number VARCHAR2(12),
  Color VARCHAR2(16),
  CONSTRAINT Auto_Color_VIN_Number_fk FOREIGN KEY(VIN_Number) 
  REFERENCES Auto(VIN_Number) ON DELETE CASCADE,
  CONSTRAINT Auto_Color_Section_pk PRIMARY KEY(Color_Section, VIN_Number));
  
  INSERT INTO AUTO_COLOR values ('Body','T1234C567891','Gray');
  INSERT INTO AUTO_COLOR values ('Trim','H2345C678910','Red');
  INSERT INTO AUTO_COLOR values ('Body','H3456S789012','Blue');

/*************************************************************/

CREATE TABLE Account
( Account_Number NUMBER(4),
  Customer_ID NUMBER(4),
  VIN_Number VARCHAR2(12),
  Start_Date DATE,
  End_Date DATE,
   "TERM(MONTH)" NUMBER(3),
  CONSTRAINT Account_Customer_ID_fk FOREIGN KEY(Customer_ID) 
  REFERENCES Customer(Customer_ID) ON DELETE CASCADE,
  CONSTRAINT Account_VIN_Number_fk FOREIGN KEY(VIN_Number) 
  REFERENCES Auto(VIN_Number) ON DELETE CASCADE,
  CONSTRAINT Account_Account_Number_pk PRIMARY KEY(Account_Number));
  
  INSERT INTO ACCOUNT VALUES (1,1,'T1234C567891',TO_DATE('01-JUN-15','DD-MON-YY'),TO_DATE('01-JUN-16','DD-MON-YY'),12);
  INSERT INTO ACCOUNT VALUES (2,2,'H2345C678910',TO_DATE('01-DEC-16','DD-MON-YY'),TO_DATE('01-JUN-17','DD-MON-YY'),18);
  INSERT INTO ACCOUNT VALUES (3,3,'H3456S789012',TO_DATE('15-FEB-17','DD-MON-YY'),TO_DATE('15-FEB-19','DD-MON-YY'),24);

/*********************************************************************************************************************/

CREATE TABLE Account_Balance
( Account_Number NUMBER(4),
  Principal_Balance NUMBER(8,2),
  Amount_Due NUMBER(5,2),
  Payment_Due_Date DATE,
  CONSTRAINT Account_Balance_Number_fk FOREIGN KEY(Account_Number) 
  REFERENCES Account(Account_Number) ON DELETE CASCADE,
  CONSTRAINT Account_Balance_Number_pk PRIMARY KEY(Account_Number));
  
  INSERT INTO ACCOUNT_BALANCE VALUES (1,0,150,TO_DATE('01-JUN-16','DD-MON-YY'));
  INSERT INTO ACCOUNT_BALANCE VALUES (2,750,250,TO_DATE('01-APR-17','DD-MON-YY'));
  INSERT INTO ACCOUNT_BALANCE VALUES (3,2530,110,TO_DATE('15-APR-17','DD-MON-YY'));

/*********************************************************************************/

CREATE TABLE Payment
( Payment_Number NUMBER(4),
  Account_Number NUMBER(4),
  Payment_Amount NUMBER(5,2),
  Payment_Date Date,
  CONSTRAINT Payment_Account_Number_fk FOREIGN KEY(Account_Number) 
  REFERENCES Account(Account_Number) ON DELETE CASCADE,
  CONSTRAINT Payment_Number_pk PRIMARY KEY(Payment_Number));
  
  INSERT INTO PAYMENT VALUES (1,1,150,TO_DATE('01-JUN-16','DD-MON-YY'));
  INSERT INTO PAYMENT VALUES (2,2,250,TO_DATE('01-MAR-17','DD-MON-YY'));
  INSERT INTO PAYMENT VALUES (3,3,110,TO_DATE('15-MAR-17','DD-MON-YY'));

/**********************************************************************/