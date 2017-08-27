package com.fbm.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "players", schema = "fbm_db")
public class Player implements Comparable<Player>{
    private long playerId;
    private String name;
    private int number;
    private int order;
    private int chance;
    private Team team;
    private List<Card> cards;

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

    @Basic
    @Column(name = "in_order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Basic
    @Column(name = "chance")
    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        if (team != this.team) {
            this.team.getPlayers().remove(this);

            team.addPlayer(this);
        }
    }

    public void addCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);

            if (card.getPlayer() != this){
                card.setPlayer(this);
            }
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.DETACH)
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.order, other.order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!name.equals(player.name)) return false;
        return team.equals(player.team);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + team.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", team: " + team.getName() +
                '}';
    }
}
