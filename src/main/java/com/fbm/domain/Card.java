package com.fbm.domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "cards", schema = "fbm_db", catalog = "")
public class Card {
    private long cardId;
    private com.fbm.domain.Player Player;
    private Set<User> users;
//todo fix imports. move to the top block
    @Id
    @Column(name = "card_id")
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @ManyToOne
    public com.fbm.domain.Player getPlayer() {
        return Player;
    }

    public void setPlayer(com.fbm.domain.Player player) {
        Player = player;
    }

    @ManyToMany(mappedBy = "cards")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", Player=" + Player +
//                ", users=" + users +
                '}';
    }
}
