package com.vogella.android.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import xavier.albanet.projetprogrammationmobile.R;

public class CardActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("Cardname") && getIntent().hasExtra("Cardclass") && getIntent().hasExtra("Cardcost") && getIntent().hasExtra("Cardrarity") && getIntent().hasExtra("Cardtype") && getIntent().hasExtra("Cardset") && getIntent().hasExtra("Cardtext")){
            String cardName = getIntent().getStringExtra("Cardname");
            String cardClass = getIntent().getStringExtra("Cardclass");
            int cardCost = getIntent().getIntExtra("Cardcost", 0);
            String cardRarity = getIntent().getStringExtra("Cardrarity");
            String cardType = getIntent().getStringExtra("Cardtype");
            String cardSet = getIntent().getStringExtra("Cardset");
            boolean cardCollectible = getIntent().getBooleanExtra("Cardcollectible", false);
            String cardText = getIntent().getStringExtra("Cardtext");
            setCard(cardName, cardClass, cardCost, cardRarity, cardType, cardSet, cardCollectible, cardText);
        }
    }

    private void setCard(String cardName, String cardClass, int cardCost, String cardRarity, String cardType, String cardSet, boolean cardCollectible, String cardText){
        TextView name = findViewById(R.id.CardName);
        name.setText("Name : " + cardName);
        TextView Class = findViewById(R.id.CardClass);
        Class.setText("Class : " + cardClass);
        TextView cost = findViewById(R.id.CardCost);
        cost.setText("Cost : " + cardCost + " mana");
        TextView rarity = findViewById(R.id.CardRarity);
        rarity.setText("Rarity : " + cardRarity);
        TextView type = findViewById(R.id.CardType);
        type.setText("Type : " + cardType);
        TextView set = findViewById(R.id.CardSet);
        set.setText("Set : " + cardSet);
        TextView collectible = findViewById(R.id.CardCollectible);
        collectible.setText("Collectible : " + cardCollectible);
        TextView text = findViewById(R.id.CardText);
        text.setText("Text : " + cardText);
    }
}