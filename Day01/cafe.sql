-- cafe
-- product_id (숫자형) -- 고유식별키
-- product_name (문자형-100) unique null값x
-- price (숫자형) null 값x
-- register_date(날짜형) null 값x
create table cafe(
 product_id number primary key
 , product_name varchar2(100) unique not null
 , price number not null
 , register_date date not null
);

-- 시퀀스 생성 -> 1부터 1씩 증가 최대값x cycle X cache X
create sequence seq_cafe
 start with 1
 increment by 1
 nomaxvalue
 nocycle
 nocache;
 
commit; -- 커밋을 잘 해야 자바에서 데이터 넣거나 가져갈때 오류 안남

select * from cafe;
insert into cafe values(seq_cafe.nextval, '자허블', 5000, sysdate);

create table tbl_student(
  no number primary key
  , name varchar2(20) not null
  , phone varchar2(20) not null
  , birth_date date not null
);


create sequence seq_std
 start with 1
 increment by 1
 nomaxvalue
 nocycle
 nocache;

 commit;
select * from tbl_student;

insert into tbl_student values(seq_std.nextval, 'sam', '010-1111-1222', to_date('1986/02/03', 'yyyy/mm/dd'));