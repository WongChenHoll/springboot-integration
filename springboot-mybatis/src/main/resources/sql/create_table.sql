
--   注意：这里的表空间是：TEST_DATA
-- Create table
create table SBI_USER
(
    id          VARCHAR2(50) not null,
    user_id     VARCHAR2(50) not null,
    user_name   VARCHAR2(100) not null,
    age         NUMBER(3),
    phone       VARCHAR2(20),
    email       VARCHAR2(100),
    dept_id     VARCHAR2(50),
    status      CHAR(1) not null,
    remark     VARCHAR2(200),
    create_time DATE not null,
    create_user VARCHAR2(100) not null,
    update_time DATE,
    update_user VARCHAR2(100)
) tablespace TEST_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Add comments to the table
comment on table SBI_USER is '用户表，用于springboot-integration项目测试使用';
-- Add comments to the columns
comment on column SBI_USER.id is '主键ID，UUID';
comment on column SBI_USER.user_id is '用户编号';
comment on column SBI_USER.user_name is '用户姓名';
comment on column SBI_USER.age is '年龄';
comment on column SBI_USER.phone is '手机号/电话号码';
comment on column SBI_USER.email is '邮箱';
comment on column SBI_USER.dept_id is '部门编号';
comment on column SBI_USER.status is '任职状态，0：离职，1:在职，2：观察，3：其他';
comment on column SBI_USER.remark is '备注';
comment on column SBI_USER.create_time is '创建时间';
comment on column SBI_USER.create_user is '创建人';
comment on column SBI_USER.update_time is '修改时间';
comment on column SBI_USER.update_user is '修改人';


-- Create table
create table SBI_DEPT
(
    id           VARCHAR2(50) not null,
    dept_id      VARCHAR2(50) not null,
    dept_name    VARCHAR2(100) not null,
    dept_manager VARCHAR2(50),
    status       CHAR(1) not null,
    dept_desc    VARCHAR2(200),
    create_time  DATE not null,
    create_user  VARCHAR2(100) not null,
    update_time  DATE,
    update_user  VARCHAR2(100)
)
    tablespace TEST_DATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table SBI_DEPT
  is '用户表，用于springboot-integration项目测试使用';
-- Add comments to the columns
comment on column SBI_DEPT.id
  is '主键ID，UUID';
comment on column SBI_DEPT.dept_id
  is '部门编号';
comment on column SBI_DEPT.dept_name
  is '部门名称';
comment on column SBI_DEPT.dept_manager
  is '部门经理';
comment on column SBI_DEPT.status
  is '部门状态，1：现存部门，0：已经不存在的部门';
comment on column SBI_DEPT.dept_desc
  is '部门描述';
comment on column SBI_DEPT.create_time
  is '创建时间';
comment on column SBI_DEPT.create_user
  is '创建人';
comment on column SBI_DEPT.update_time
  is '修改时间';
comment on column SBI_DEPT.update_user
  is '修改人';


-- Create table
create table ACCOUNT
(
    id    VARCHAR2(50),
    name  VARCHAR2(50),
    money NUMBER(5,2)
)
    tablespace TEST_DATA;
