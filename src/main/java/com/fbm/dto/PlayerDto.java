package com.fbm.dto;

import java.util.Set;

/**
 * Created by Mocart on 21-Aug-17.
 */
public class PlayerDto implements Comparable<PlayerDto> {
    private long id;
    private int order;
    Set<CardDto> cards;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Set<CardDto> getCards() {
        return cards;
    }

    public void setCards(Set<CardDto> cards) {
        this.cards = cards;
    }

    @Override
    public int compareTo(PlayerDto other) {
        return Integer.compare(this.order, other.order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDto playerDto = (PlayerDto) o;

        return id == playerDto.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
