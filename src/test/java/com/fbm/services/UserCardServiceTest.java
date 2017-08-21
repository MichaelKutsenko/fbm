package com.fbm.services;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import com.fbm.domain.UserCard;
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
public class UserCardServiceTest {
    @Autowired
    private UserCardService userCardService;

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Test
    @Transactional
    public void getByUserAndCardIds() throws Exception {
        System.out.println(userCardService.getByUserIdAndCardIds(1, 4));

        assertThat(userCardService.getByUserIdAndCardIds(1, 4).isActivated()).isTrue();
        assertThat(userCardService.getByUserIdAndCardIds(1, 5).isActivated()).isFalse();
    }

    @Test
    @Transactional
    public void updateUserCard() throws Exception {
        UserCard userCard = userCardService.getByUserIdAndCardIds(1, 4);
        userCardService.updateStatusOfCard(userCard, false);
        assertThat(userCardService.getByUserIdAndCardIds(1, 4).isActivated()).isFalse();
    }

    @Test
    public void deleteUserCardTest() throws Exception {
        User user = userService.getById(1);
        int initCardSize = user.getCards().size();


        Card card = cardService.getById(3);

        userCardService.deleteUserCard(user, card);

        int tempSizeOfCards = user.getCards().size();
        user = userService.getById(1);
        int cardSize = user.getCards().size();

        System.out.println("-------------------------");
        System.out.println(initCardSize == cardSize);
        System.out.println(initCardSize);
        System.out.println(tempSizeOfCards);
        System.out.println(cardSize);
        System.out.println("-------------------------");
    }
}