package com.fbm.services;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mocart on 31-Jul-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void getAllUsers() throws Exception {
        System.out.println(userService.getAllUsers());

        List<User> list = userService.getAllUsers();

        for (Card card : list.get(0).getCards()){
            System.out.println("hello");
            System.out.println("hello");
            System.out.println("hello");
            System.out.println(card.getPlayer());
        }
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