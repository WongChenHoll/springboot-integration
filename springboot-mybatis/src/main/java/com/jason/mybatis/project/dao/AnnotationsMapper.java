package com.jason.mybatis.project.dao;

import com.jason.mybatis.project.model.Account;
import com.jason.mybatis.project.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 使用Mybatis中的注解形式。
 * <p/>
 * 不需要使用XML配置文件，直接在接口中编写SQL。
 * 当参数是对象时会直接根据名字解析对应的属性。
 *
 * @author WongChenHoll
 * @date 2022-4-7 15:29
 **/
public interface AnnotationsMapper {

    /**
     * 通过@Insert注解新增数据。
     *
     * @param user 参数
     */
    @Insert("insert into sbi_user" +
            "(id, user_Id, user_name, age, phone, email, dept_id, status, remark, create_time, create_user)" +
            "values" +
            "(sys_guid(), #{userId}, #{userName}, #{age}, #{phone}, #{email}, #{deptId}, #{status}, #{remark}, sysdate, #{createUser})")
    void addUser(UserModel user);


//      ==================== 下面的例子来自官网：https://mybatis.org/mybatis-3/zh/java-api.html ====================

//    这个例子展示了如何使用 @SelectKey 注解来在插入前读取数据库序列的值：
//
//    @Insert("insert into table3 (id, name) values(#{nameId}, #{name})")
//    @SelectKey(statement="call next value for TestSequence", keyProperty="nameId", before=true, resultType=int.class)
//    int insertTable3(Name name);
//    这个例子展示了如何使用 @SelectKey 注解来在插入后读取数据库自增列的值：
//
//    @Insert("insert into table2 (name) values(#{name})")
//    @SelectKey(statement="call identity()", keyProperty="nameId", before=false, resultType=int.class)
//    int insertTable2(Name name);

//      ================================================ 官网的例子结束 ==============================================

//    @Insert("insert into account (id,name,money) values (#{id}, #{name}, #{money})")
//    @SelectKey(statement = "select test_seq.nextval from dual", keyProperty = "id", before = true, resultType = int.class)
//    int addAccount(@Param("name") String name, @Param("money") double money);

    /**
     * 使用@Insert注解添加数据，使用@SelectKey注解将新数据的主键ID返回到参数对象中去。
     *
     * @param account 参数对象
     */
    @Insert("insert into account (id,name,money) values (#{id}, #{name}, #{money})")
    @SelectKey(statement = "select test_seq.nextval from dual", keyProperty = "id", before = true, resultType = int.class)
    void addAccount(Account account);


    /**
     * 通过@Select注解查询数据。使用@Results定义一个结果集（通过ResultMap标签一样）
     *
     * @param userName 用户名
     * @param age      年龄
     * @return 用户列表
     */
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name")
    })
    @Select("select id,user_Id,user_name,email,age,status from sbi_user where user_name like '%'||#{userName}||'%' and age > #{age}")
    List<UserModel> userList(@Param("userName") String userName, @Param("age") int age);


    /**
     * 使用@SelectProvider注解查询数据。
     * <p/>
     * 使用该注解时需要创建一个类，用于构建查询的SQL。
     * <p/>
     * type：指定构建sql的类，method：指定构建sql的方法
     *
     * @param userName 用户名
     * @param age      年龄
     * @return 用户列表年龄
     */
    @SelectProvider(type = UserSqlBuilder.class, method = "buildUserSql")
    List<UserModel> selectUserListByNameAndAge(@Param("userName") String userName, @Param("age") int age);

    /**
     * 联合查询
     */
    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUserByDeptIdSql")
    List<UserModel> selectDeptUserList(String deptId);

    /**
     * 构建查询SQL，仅限于查询的SQL
     */
    class UserSqlBuilder {
        /**
         * 构建根据用户名和年龄查询的SQL
         *
         * @param userName 用户名
         * @param age      年龄
         * @return 用户查询SQL
         */
        public static String buildUserSql(final String userName, final int age) {
            return new SQL() {{
                SELECT("ID", "USER_ID userId", "USER_NAME userName", "EMAIL", "AGE", "STATUS");
                FROM("SBI_USER");
                if (userName != null && !"".equals(userName)) {
                    WHERE("USER_NAME LIKE '%'||#{userName}||'%'");
                }
                WHERE("age > #{age}");
                ORDER_BY("AGE DESC");
            }}.toString();
        }

        /**
         * 两张表联合查询
         *
         * @param deptId 部门主键ID
         * @return 查询SQL
         */
        public static String buildGetUserByDeptIdSql(final String deptId) {
            return new SQL() {{
                SELECT("A.ID,A.USER_ID userId,A.USER_NAME userName,A.DEPT_ID deptId");
                FROM("SBI_USER A");
                LEFT_OUTER_JOIN("SBI_DEPT B ON A.DEPT_ID = B.DEPT_ID");
                WHERE("B.ID = #{deptId}");
            }}.toString();
        }
    }


}
