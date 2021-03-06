package com.fbm.repository;


import com.fbm.domain.Card;
import com.fbm.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Mocart on 07-Jul-17.
 */
public interface CardRepository extends CrudRepository<Card, Long> {
    @Override
    List<Card> findAll();

    List<Card> findByPlayer_PlayerId(long playerId);

    List<Card> findByUsers_userId(long userId);

    List<Card> findByUsers(User user);

    List<Card> findByUsers_UserIdAndPlayer_Team_Name(long userId, String teamName);

    List<Card> findByUsers_UserIdAndPlayer_Team_TeamId(long userId, long teamId);

    List<Card> findByPlayer_Team_TeamId(long teamId);

    Card findCardByChanceAndPlayer_Name(int chance, String playerName);

//    List<Card> findByUser_UserIdAndRelations_isActivated(long userId, boolean isActivated);
}