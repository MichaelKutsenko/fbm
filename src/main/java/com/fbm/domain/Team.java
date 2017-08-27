package com.fbm.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "teams", schema = "fbm_db", catalog = "")
public class Team implements Comparable<Team>{
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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.DETACH)
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
    public int compareTo(Team o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (!name.equals(team.name)) return false;
        return country.equals(team.country);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
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
