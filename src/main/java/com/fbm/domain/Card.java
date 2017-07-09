package com.fbm.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Mocart on 09-Jul-17.
 */
@Entity
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CARD")
@Table(name = "cards", schema = "fbm_db", catalog = "")
public class Card {
    private long cardId;
    private byte[] photo;
    private Player player;
    private Set<User> users;

    @Id
    @Column(name = "card_id")
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardId != card.cardId) return false;
        if (!Arrays.equals(photo, card.photo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @ManyToOne
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
}
