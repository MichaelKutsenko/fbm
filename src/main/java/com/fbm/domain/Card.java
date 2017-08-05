package com.fbm.domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

/**
 * Represents a player card. Each player have few type of cards (f.e. cartoon card, official card and so on).
 * Created by Mocart on 09-Jul-17.
 *
 * @see CardCartoon
 * @see CardOfficial
 */
@Entity
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING, length=16)
@DiscriminatorValue("CARD")
@Table(name = "cards", schema = "fbm_db", catalog = "")
public class Card {
    private long cardId;
    //todo fix imports
    private com.fbm.domain.Player Player;
    private Set<User> users;
    private int chance;

    public Card() {
        this.chance = 100;
    }

    @Id
    @Column(name = "card_id")
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "chance")
    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @ManyToOne(cascade = CascadeType.DETACH, fetch = EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", nullable = false)
    public com.fbm.domain.Player getPlayer() {
        return Player;
    } //todo fix imports. move to the top block

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
