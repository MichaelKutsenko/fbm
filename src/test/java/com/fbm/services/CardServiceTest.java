package com.fbm.services;

import com.fbm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by Mocart on 06-Aug-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardServiceTest {
    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void getAllCards() throws Exception {
        User user = userService.getById(1);

        assertThat(cardService.getAllCards().size()).isEqualTo(10);
        assertThat(cardService.getByPlayerId(2).size()).isEqualTo(2);
        assertThat(cardService.getByUserId(1).size()).isEqualTo(5);
        assertThat(cardService.getByUser(user).size()).isEqualTo(5);

        System.out.println("===================");
        System.out.println(cardService.getByUser(user));
    }

    @Test
    public void getListByPlayerId() throws Exception {
    }

    @Test
    public void saveCard() throws Exception {
    }

    @Test
    public void updateCard() throws Exception {
    }

}