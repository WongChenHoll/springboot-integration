package com.jason.mybatisplus.mappertest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mybatisplus.project.mapper.UserMapper;
import com.jason.mybatisplus.project.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试使用MybatisPlus时，调用Dao层接口中BaseMapper中默认的方法
 *
 * @author WongChenHoll
 * @date 2022-4-11 15:53
 **/
@SpringBootTest
public class TestMapperSelect {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查询单个对象
     */
    @Test
    public void testSelectOne() {
        // 创建条件封装器
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("ID", "2685B25BC4A84E2C87D394E7413A7214");

        // 查询
        UserModel userModel = userMapper.selectOne(wrapper);

        // 输出结果
        System.out.println(userModel.toString());
    }

    /**
     * 模糊查询列表和查询总数
     */
    @Test
    public void testSelectLikeAndCount() {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.like("USER_NAME", "1");

        // 模糊查询列表：姓名中有1的列表
        List<UserModel> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);

        // 查询总数
        Long count = userMapper.selectCount(wrapper);
        System.out.println("总数：" + count);
    }

    // 多个条件查询
    @Test
    public void testSelectMoreCondition() {

        // SELECT id,USER_ID,USER_NAME,AGE,PHONE,EMAIL,DEPT_ID,STATUS,REMARK,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER
        // FROM SBI_USER WHERE (STATUS = ? AND USER_NAME LIKE ? AND AGE BETWEEN ? AND ?) ORDER BY AGE DESC
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("STATUS", "1"); // 查询status=1
        wrapper.like("USER_NAME", "3"); // 查询姓名中包含3
        wrapper.between("AGE", 10, 30); // 查询年龄在10-30之间
        wrapper.orderByDesc("AGE"); // 根据年龄倒序
        List<UserModel> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);

        System.out.println("========================================================");
        // 查询年龄大于等于30，或者名字中包含3的用户列表
        // SELECT id,USER_ID,USER_NAME,AGE,PHONE,EMAIL,DEPT_ID,STATUS,REMARK,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER
        // FROM SBI_USER WHERE (age > ? OR user_name LIKE ?)
        QueryWrapper<UserModel> orQuery = new QueryWrapper<>();
        orQuery.gt("age", 30);
        orQuery.or();
        orQuery.like("user_name", "3");
        List<UserModel> userModels = userMapper.selectList(orQuery);
        userModels.forEach(System.out::println);
    }

    /**
     * 先对参数条件进行判断，否和要求再创建条件构造器，进行条件查询
     */
    @Test
    public void testSelectListByCondition() {
        // SELECT id,USER_ID,USER_NAME,AGE,PHONE,EMAIL,DEPT_ID,STATUS,REMARK,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER
        // FROM SBI_USER WHERE (user_name NOT LIKE ? AND age <= ?)
        List<UserModel> list1 = userMapper.selectList(buildQueryWrapper("张三", 100));
        list1.forEach(System.out::println);

        // SELECT id,USER_ID,USER_NAME,AGE,PHONE,EMAIL,DEPT_ID,STATUS,REMARK,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER
        // FROM SBI_USER WHERE (user_name NOT LIKE ?)
        List<UserModel> list2 = userMapper.selectList(buildQueryWrapper("张三", -1));
        list2.forEach(System.out::println);

        // SELECT id,USER_ID,USER_NAME,AGE,PHONE,EMAIL,DEPT_ID,STATUS,REMARK,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER
        // FROM SBI_USER WHERE (age <= ?)
        List<UserModel> list3 = userMapper.selectList(buildQueryWrapper(null, 10));
        list3.forEach(System.out::println);
    }

    private QueryWrapper<UserModel> buildQueryWrapper(String userNameParam, int ageParam) {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        // 如果用户名userNameParam不为空时，则查询用户名中不包含userNameParam的用户
        wrapper.notLike((userNameParam != null && !"".equals(userNameParam)), "user_name", userNameParam);
        // 如果年龄值大于0，则查询年龄小于等于ageParam的用户
        wrapper.le(ageParam > 0, "age", ageParam);
        return wrapper;
    }

    /**
     * 分页查询
     * <p/>
     * 使用分页功能时必须先配置分页的插件{@link com.jason.mybatisplus.config.MybatisPlusConfig}。
     */
    @Test
    public void testSelectPage() {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.select("ID", "USER_ID", "USER_NAME", "AGE", "PHONE", "EMAIL", "DEPT_ID", "STATUS");
        wrapper.notBetween("AGE", 0, 30);
        wrapper.orderByDesc("AGE");

        // 分页参数
        Page<UserModel> page = new Page<>();
        page.setCurrent(1); // 设置查询第几页
        page.setSize(5); // 设置每页查询的数量
        page = userMapper.selectPage(page, wrapper);

        System.out.println("总页数：" + page.getPages());
        System.out.println("总数量：" + page.getTotal());
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testPage() {
        UserModel model = new UserModel();
        model.setAge(30);

        Page<UserModel> page = new Page<>();
        page.setSize(5);
        page.setCurrent(1);

        List<UserModel> list = userMapper.pageList(page, model);
        list.forEach(System.out::println);
    }
}
