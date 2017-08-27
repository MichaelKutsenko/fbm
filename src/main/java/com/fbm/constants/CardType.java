package com.fbm.constants;

public enum CardType {
    CARTOON("CARTOON"), OFFICIAL("OFFICIAL"), CARD("CARD");

    String type;

    private CardType(String cardType) {
        this.type = cardType;
    }

    public String getType() {
        return type;
    }
}
