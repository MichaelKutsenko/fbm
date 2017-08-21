package com.fbm.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "teams", schema = "fbm_db", catalog = "")
public class Team {
    private long teamId;
    private String name;
    private Country country;
    private List<Player> players;
    private int chance;

    public Team() {
        chance = 100;
    }

    @Id
    @Column(name = "team_id")
    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "chance")
    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.DETACH, fetch = EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.DETACH, fetch = EAGER)
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)){
            players.add(player);

            if (player.getTeam() != this) {
                player.setTeam(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamId != team.teamId) return false;
        if (name != null ? !name.equals(team.name) : team.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (teamId ^ (teamId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", players: " + players.size() +
                '}';
    }
}
