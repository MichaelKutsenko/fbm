package com.fbm.services;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import com.fbm.domain.UserCard;
import com.fbm.repository.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Mocart on 06-Aug-17.
 */
@Service
public class UserCardService {
    @Autowired
    private UserCardRepository userCardRepository;

    public UserCard getByUserIdAndCardIds(long userId, long cardId) {
        return userCardRepository.findByUser_UserIdAndCard_CardId(userId, cardId);
    }

    public Set<UserCard> getByUserIdAndPlayerId(long userId, long playerId) {
        return userCardRepository.findByUser_UserIdAndCard_Player_PlayerId(userId, playerId);
    }

    public UserCard addUserCard(User user, Card card) {
        UserCard uc = new UserCard();
        uc.setUser(user);
        uc.setCard(card);

        return userCardRepository.save(uc);
    }

    public UserCard addUserCard(UserCard userCard) {
        return userCardRepository.save(userCard);
    }

    public UserCard addUserCard(User user, Card card, boolean isActivated) {
        UserCard uc = new UserCard();
        uc.setUser(user);
        uc.setCard(card);
        uc.setActivated(isActivated);

        return userCardRepository.save(uc);
    }

    public UserCard addUserCard(UserCard userCard, boolean isActivated) {
        userCard.setActivated(isActivated);
        return userCardRepository.save(userCard);
    }

    public UserCard updateStatusOfCard(User user, Card card, boolean isCardActivated) {
        UserCard userCard = userCardRepository.findByUserAndCard(user, card);
        userCard.setActivated(isCardActivated);

        return userCardRepository.save(userCard);
    }

    public UserCard updateStatusOfCard(UserCard userCard, boolean isCardActivated) {
        userCard.setActivated(isCardActivated);

        return userCardRepository.save(userCard);
    }

    public void deleteUserCard(long userId, Long cardId) {
        UserCard userCard = getByUserIdAndCardIds(userId, cardId);

        userCardRepository.delete(userCard);
    }

    public void deleteUserCard(User user, Card card) {
        UserCard userCard = userCardRepository.findByUserAndCard(user, card);

        userCardRepository.delete(userCard);
    }

    public void deleteUserCard(UserCard userCard) {
        userCardRepository.delete(userCard);
    }

    public boolean containsId(long userCardId) {
        return userCardRepository.exists(userCardId);
    }
}
