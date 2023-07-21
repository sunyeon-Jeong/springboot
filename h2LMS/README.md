## MAJOR TABLE
``` sql
CREATE TABLE IF NOT EXISTS MAJOR
(
    major_code varchar(100) primary key comment '주특기코드',
    major_name varchar(100) not null comment '주특기명',
    tutor_name varchar(100) not null comment '튜터'
)
```

## STUDENT TABLE
``` sql
CREATE TABLE IF NOT EXISTS STUDENT
(
    student_code varchar(100) primary key comment '수강생코드',
    name varchar(100) not null comment '이름',
    birth varchar(8) null comment '생년월일',
    gender varchar(1) not null comment '성별',
    phone varchar(11) null comment '전화번호',
    major_code varchar(100) not null comment '주특기코드',
    foreign key (major_code) references MAJOR(major_code)
)
```

## EXAM TABLE
```sql
CREATE TABLE IF NOT EXISTS EXAM
(
    student_code varchar(100) primary key comment '수강생코드',
    exam_seq int not null comment '시험주차',
    score decimal(10, 2) not null comment '시험점수',
    result varchar(1) not null comment '합격여부'
);
```