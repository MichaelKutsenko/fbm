package com.fbm.constants;

/**
 * Created by Mocart on 06-Aug-17.
 */
public enum OrderOfPlayer {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11), OTHER(999);

    private int order;

    OrderOfPlayer(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
