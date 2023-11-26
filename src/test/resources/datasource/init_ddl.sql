-- Ref: Copy to src/main/resources/schema.sql
use mydatabase;
CREATE TABLE EMPLOYEE
(
    EMPLOYEE_NUMBER int(10) unsigned auto_increment primary key COMMENT '社員番号は0-9999999999までの数値とする．',
    FULL_NAME       varchar(100)        NOT NULL COMMENT '氏名:推奨値は20だが,100まで許容する.許容文字については別途検討する．',
    AGE             TINYINT(3) unsigned NOT NULL COMMENT '年齢:0-200歳までをサービス利用者として想定しその間を許容文字数とする．',
    EMAIL_ADDRESS   varchar(255)        NOT NULL COMMENT 'メールアドレス:RFC5321より10文字以上-254文字以内を許容文字数とする.許容文字については別途検討する.',
    INDEX index_name_employee (EMPLOYEE_NUMBER)
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_bin;
