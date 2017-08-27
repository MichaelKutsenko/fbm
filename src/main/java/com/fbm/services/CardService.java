package com.fbm.services;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import com.fbm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Mocart on 30-Jul-17.
 */

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card getById(long id) {
        return cardRepository.findOne(id);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    /**
     * @param playerId the football player which cards to be got
     * @return all cards of the specified player (each player can have few type of cards)
     */
    public List<Card> getByPlayerId(long playerId) {
        return cardRepository.findByPlayer_PlayerId(playerId);
    }

    /**
     * @param userId the user ID
     * @return all cards of the specified user.
     */
    public List<Card> getByUserId(long userId) {
        return cardRepository.findByUsers_userId(userId);
    }

    public List<Card> getByUser(User user) {
        return cardRepository.findByUsers(user);
    }

    public List<Card> getByUserIdAndTeamName(long userId, String teamName) {
        return cardRepository.findByUsers_UserIdAndPlayer_Team_Name(userId, teamName);
    }

    public List<Card> getByUserIdAndTeamId(long userId, long teamId) {
        return cardRepository.findByUsers_UserIdAndPlayer_Team_TeamId(userId, teamId);
    }

    public List<Card> getByTeamId(long teamId) {
        return cardRepository.findByPlayer_Team_TeamId(teamId);
    }

    public Card getByChanceAndPlayerName(int chanse, String playerName) {
        return cardRepository.findCardByChanceAndPlayer_Name(chanse, playerName);
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteById(long cardId) {
        cardRepository.delete(cardId);
    }
}
