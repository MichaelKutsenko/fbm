package domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mocart on 06-Jul-17.
 */
@Entity
@Table(name = "players", schema = "fbm_db", catalog = "")
public class Player {
    private long playerId;
    private String name;
    private int number;
    private Team team;
    private Set<Card> cards;

    @Id
    @Column(name = "player_id")
    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "player")
    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerId != player.playerId) return false;
        if (number != player.number) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (playerId ^ (playerId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", team=" + team +
                ", cards=" + cards +
                '}';
    }
}
