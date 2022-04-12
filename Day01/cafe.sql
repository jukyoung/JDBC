-- cafe
-- product_id (������) -- �����ĺ�Ű
-- product_name (������-100) unique null��x
-- price (������) null ��x
-- register_date(��¥��) null ��x
create table cafe(
 product_id number primary key
 , product_name varchar2(100) unique not null
 , price number not null
 , register_date date not null
);

-- ������ ���� -> 1���� 1�� ���� �ִ밪x cycle X cache X
create sequence seq_cafe
 start with 1
 increment by 1
 nomaxvalue
 nocycle
 nocache;
 
commit; -- Ŀ���� �� �ؾ� �ڹٿ��� ������ �ְų� �������� ���� �ȳ�

select * from cafe;
insert into cafe values(seq_cafe.nextval, '�����', 5000, sysdate);

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