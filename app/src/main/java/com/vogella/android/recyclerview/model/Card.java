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
    private String id;
    private boolean isFav;


    public String getCardClass() {
        if (cardClass == "NEUTRAL") {
            return "ALL";
        }
        else {
            return cardClass;
        }
    }

    public String isCollectible() {
        if (collectible) {
            return "This card is currently craftable";
        } else {
            return "This card is currently not craftable";
        }
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
        if (text != null) {
            text = text.replace("<b>", "");
            text = text.replace("</b>", " ");
            text = text.replace("#", "");
            text = text.replace("<i>", "");
            text = text.replace("</i>", "");
            text = text.replace("[x]", "");
            text = text.replace("$", "");
        }
        return text;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isFav() { return isFav; }

    public void changeFav () {isFav = !isFav;}
}
