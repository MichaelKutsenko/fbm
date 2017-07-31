package com.fbm.services;

import com.football.fbm.domain.Card;
import com.football.fbm.repository.CardRepository;
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
    public List<Card> getListByPlayerId(long playerId){
//        return cardRepository.findByPlayer_PlayerId(playerId);
        return cardRepository.findByPlayer_PlayerId(playerId);
    }
//
//    /**
//     * @param userId the user ID
//     * @return all cards of the specified user.
//     */
//    public List<Card> getListByUserId(long userId){
//        return cardRepository.findByUsers_userId(userId);
//    }
//
//    /**
//     * @param userName the user name
//     * @return all cards of the specified user.
//     */
//    public List<Card> getListByUserName(String userName){
//        return cardRepository.findByUsers_UserName(userName);
//    }
//
//    public List<Card> getListByUserIdAndTeamId(long userId, long teamId){
//        return cardRepository.findAllByUsers_UserIdAnAndAndPlayer_Team_TeamId(userId, teamId);
//
//    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }
}
