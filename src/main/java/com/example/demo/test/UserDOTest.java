package com.example.demo.test;

import com.example.demo.entity.UserDo;
import com.example.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDOTest {
    @Autowired
    UserService userService;

    @Before
    public void before() {

    }

    @Test
    public void testAdd() {
        userService.queryByName("666");

    }

//    @After
//    public void after() {
//        userService.deleteById(1L);
//        userService.deleteById(3L);
//        userService.deleteById(5L);
//    }
}
