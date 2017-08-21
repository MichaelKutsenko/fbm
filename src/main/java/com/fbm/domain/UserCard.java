package com.fbm.domain;


import javax.persistence.*;

/**
 * Created by Mocart on 06-Aug-17.
 */
@Entity
@Table(name = "user_card", schema = "fbm_db", catalog = "")
public class UserCard {
    private long userCardId;
    private boolean isActivated;
    private User user;
    private Card card;

    public UserCard() {
    }

    public UserCard(User user, Card card) {
        this.user = user;
        this.card = card;
    }

    @Id
    @Column(name = "user_card_id")
    public long getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(long userCardId) {
        this.userCardId = userCardId;
    }

    @Basic
    @Column(name = "is_activated")
    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false)
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "userCardId=" + userCardId +
                ", isActivated=" + isActivated +
                ", user=" + user +
                ", card=" + card +
                '}';
    }
}
