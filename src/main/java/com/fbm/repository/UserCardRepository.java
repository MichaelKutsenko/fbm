package com.fbm.repository;

import com.fbm.domain.Card;
import com.fbm.domain.User;
import com.fbm.domain.UserCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Mocart on 06-Aug-17.
 */
public interface UserCardRepository extends CrudRepository<UserCard, Long> {

    UserCard findByUser_UserIdAndCard_CardId(long userId, long cardId);

    UserCard findByUserAndCard(User user, Card card);

    Set<UserCard> findByUser_UserIdAndCard_Player_PlayerId(long userId, long cardId);
}
