package com.fbm.services;

import com.fbm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by Mocart on 31-Jul-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        List<User> list = userService.getAllUsers();

        assertThat(list.size()).isEqualTo(3);

        User user = userService.getById(2);
        userService.deleteById(3);
        userService.deleteUser(user);

        assertThat(userService.getAllUsers().size()).isEqualTo(1);
    }

    @Test
    public void getUserById() throws Exception {
    }

    @Test
    public void saveUser() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

}