package com.vogella.android.recyclerview.model;

public class Card {
    private String cardClass;
    private boolean collectible;
    private int cost;
    private String name;
    private String rarity;
    private String set;
    private String text;
    private String type;

    public String getCardClass() {
        return cardClass;
    }

    public boolean isCollectible() {
        return collectible;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getSet() {
        return set;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}
