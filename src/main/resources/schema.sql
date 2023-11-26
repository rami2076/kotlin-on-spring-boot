-- Ref: Copy from src/test/resources/datasource/init_ddl.sql
create table EMPLOYEE
(
    EMPLOYEE_NUMBER int          not null AUTO_INCREMENT,
    FULL_NAME       varchar(100) not null,
    AGE             tinyint,
    EMAIL_ADDRESS   varchar(255) not null,
    PRIMARY KEY (EMPLOYEE_NUMBER)
);
