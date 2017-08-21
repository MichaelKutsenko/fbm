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
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING, length = 16)
@DiscriminatorValue("CARD")
@Table(name = "cards", schema = "fbm_db")
public class Card implements Comparable<Card>{
    private long cardId;
    private Player player;
    private Set<User> users;
    private int chance;

    private Set<UserCard> relations;

    public Card() {
        this.chance = 100;
    }

    public String determinateType() {
        return "CARD";
    }

    @OneToMany(mappedBy = "card")
    public Set<UserCard> getRelations() {
        return relations;
    }

    public void setRelations(Set<UserCard> relations) {
        this.relations = relations;
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
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToMany(mappedBy = "cards")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!users.contains(user)){
            user.addCard(this);
        }
    }

    public void delete(User user) {
        if (users.contains(user)){
            users.remove(user);
            user.getCards().remove(this);
        }
    }

    @Override
    public int compareTo(Card other) {
        return this.determinateType().compareTo(other.determinateType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return cardId == card.cardId;
    }

    @Override
    public int hashCode() {
        return (int) (cardId ^ (cardId >>> 32));
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", player=" + player +
//                ", users=" + users +
                '}';
    }
}
