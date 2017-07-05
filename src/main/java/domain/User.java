package domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mocart on 06-Jul-17.
 */
@Entity
@Table(name = "users", schema = "fbm_db", catalog = "")
public class User {
    private long userId;
    private String userName;
    private String pswrdHash;
    private String email;
    private Set<Card> cards;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "pswrd_hash")
    public String getPswrdHash() {
        return pswrdHash;
    }

    public void setPswrdHash(String pswrdHash) {
        this.pswrdHash = pswrdHash;
    }

    @ManyToMany
    @JoinTable(name = "user_card", catalog = "", schema = "fbm_db",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false))
    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (pswrdHash != null ? !pswrdHash.equals(user.pswrdHash) : user.pswrdHash != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (pswrdHash != null ? pswrdHash.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", pswrdHash='" + pswrdHash + '\'' +
                ", email='" + email + '\'' +
                ", cards=" + cards +
                '}';
    }
}
