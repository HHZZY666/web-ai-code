package org.example;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //SpringBoot单元测试的注解 - 当测试类中的测试方法运行时，会自动启动SpringBoot项目 - IOC容器
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(5);
    }

    @Test
    public void testInsert() {
        userMapper.insert(new User(null, "gaoyuanyuan", "666888", "高圆圆", 18));
    }

    @Test
    public void testUpdate() {
        userMapper.update(new User(1, "zhouyu", "666888", "周瑜", 20));
    }

    @Test
    public void testSelectByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("zhouyu", "666888");
        System.out.println(user);
    }

}
