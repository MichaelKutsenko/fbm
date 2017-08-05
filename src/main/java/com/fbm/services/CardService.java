package com.fbm.services;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import com.fbm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mocart on 30-Jul-17.
 */

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    /**
     * @param playerId the football player which cards to be got
     * @return all cards of the specified player (each player can have few type of cards)
     */
    public List<Card> getByPlayerId(long playerId){
        return cardRepository.findByPlayer_PlayerId(playerId);
    }

    /**
     * @param userId the user ID
     * @return all cards of the specified user.
     */
    public List<Card> getByUserId(long userId){
        return cardRepository.findByUsers_userId(userId);
    }

    public List<Card> getByUser(User user) {
        return cardRepository.findByUsers(user);
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }
}
