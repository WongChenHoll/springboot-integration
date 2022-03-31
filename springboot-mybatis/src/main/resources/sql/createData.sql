
-- 给表：sbi_user 造数据

truncate table sbi_user;

declare
    p_user_Id   varchar2(50);
    p_user_name varchar2(100);
    p_age       NUMBER(3);
    p_dept_id   varchar2(50);
begin
    for i in 1 .. 50 loop

            p_user_Id   := 'P_' || LPAD(i, 5, '0');
            p_user_name := '张三' || i;
            p_age       := i;

            if i <= 10 then
                p_dept_id := 'D001';
            elsif 10 < i and i <= 20 then
                p_dept_id := 'D002';
            elsif 20 < i and i <= 30 then
                p_dept_id := 'D003';
            elsif 30 < i and i <= 40 then
                p_dept_id := 'D004';
            elsif 40 < i and i <= 50 then
                p_dept_id := 'D005';
            end if;

            insert into sbi_user
            (id,
             user_Id,
             user_name,
             age,
             phone,
             email,
             dept_id,
             status,
             remark,
             create_time,
             create_user)
            values
            (sys_guid(),
             p_user_Id,
             p_user_name,
             p_age,
             '18622226666',
             '18622226666@186.com',
             p_dept_id,
             '1',
             null,
             sysdate,
             'admin');
            commit;
        end loop;
end;

