Username : E1082926
Password: E1082926

username and password are changed

create table cms_vendor(

v_id varchar2(10) primary key,
v_name varchar2(30) not null,
v_pwd varchar2(30) default 'tcs@1234' not null,
v_email varchar2(30),
v_phone number(10) not null,
v_active number(1) default 0 check (v_active in (1,0)),
v_type varchar2(20) not null
);
select * from CMS_VENDOR; where v_id = 1001
create sequence v_seq start with 10001 increment by 1; 
create sequence i_seq start with 101 increment by 1; 

insert into CMS_VENDOR values('V10001','Saurabh','Tcs@1234','saurabh@gmail.com',
							  1234567890,1,'drinks');

drop table cms_vendor;
drop table cms_transaction;
drop table cms_admin;
drop table cms_item;
drop table cms_employee;

create table cms_admin(
a_id varchar2(10) primary key,
a_name varchar2(30) not null,
a_pwd varchar2(30) default 'tcs@1234' not null,
a_email varchar2(30),
a_phone number(10) not null,
a_active number(1) default 1 check (a_active in (1,0))

);

insert into CMS_ADMIN values('A1096','Saurabh','as','saurabh@gmail.com',
							  1234567890,1);

create table cms_employee(
e_id varchar2(10) primary key,
e_name varchar2(30) not null,
e_pwd varchar2(30) default 'tcs@1234' not null,
e_email varchar2(30),
e_phone number(10) not null,
e_active number(1) default 1 check (e_active in (1,0))
);



insert into cms_employee values(102,'emp1','pwd1','erruchirgupta@gmail.com',9415157839,1);

insert into CMS_EMPLOYEE values(101,'Rahul','pwd','hah',146,1);


select * from CMS_EMPLOYEE

select * from CMS_EMPLOYEE

insert into cms_employee values(101,'emp','pwd');


create table cms_item(
i_id int primary key,
v_id varchar2(10) references cms_vendor(v_id),
i_name varchar2(30) not null,
i_price number(10,2) not null,
i_type varchar2(30),
i_mo number(1) default 0 check (i_mo in (1,0)),
i_tu number(1) default 0 check (i_tu in (1,0)),
i_we number(1) default 0 check (i_we in (1,0)),
i_th number(1) default 0 check (i_th in (1,0)),
i_fr number(1) default 0 check (i_fr in (1,0)),
i_sa number(1) default 0 check (i_sa in (1,0)),
i_su number(1) default 0 check (i_su in (1,0)),
i_status number(1) default 0 check (i_status in (0,1,2)),
i_update number(1) default 0 check (i_update in (0,1,2)),
i_reject_cmt varchar2(100)
);

alter table cms_item modify i_status number(1) default 0 check (i_status in (0,1,2));

alter table cms_item modify i_update number(1) default 0 check (i_update in (0,1,2,3));

alter table cms_item add serve_count int default 0;

select * from CMS_ITEM;

drop table cms_item_temp;

create table cms_item_temp(
i_id int references cms_item(i_id),
v_id varchar2(10) references cms_vendor(v_id),
i_name varchar2(30) not null,
i_price number(10,2) not null,
i_reject_status number(1) default 0 check (i_reject_status in (0,1)),
i_update_cmt varchar2(100),
i_reject_cmt varchar2(100)
);

create table cms_wallet(
e_id varchar2(10) references cms_employee(e_id),
v_id varchar2(10) references cms_vendor(v_id),
money number(10,2)
);

SELECT * FROM CMS_WALLET

SELECT * FROM CMS_WALLET WHERE E_ID=1 AND V_ID=1;


insert into CMS_WALLET values('1213330','V10001',30000);
create table cms_transcation(
t_id int primary key,
t_eid varchar2(10) references cms_employee(e_id) not null,
t_vid varchar2(10) references cms_vendor(v_id) not null,
t_date timestamp not null,
t_amount number(10,2) not null,
t_type varchar2(10) check(t_type in('debit','credit'))
);


create table cms_reimburseAmount(
v_id varchar2(10) references cms_vendor(v_id),
e_id varchar2(10) references cms_employee(e_id),
amount number(10,2) default 0
);

create table cms_securityAnswer(
u_id varchar2(10) NOT NULL UNIQUE,
answer1 varchar2(30),
answer2 varchar2(30),
answer3 varchar2(30),
answer4 varchar2(30)
);

