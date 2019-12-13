package com.vogella.android.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import xavier.albanet.projetprogrammationmobile.R;

public class CardActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("Cardname") && getIntent().hasExtra("Cardclass") && getIntent().hasExtra("Cardcost") && getIntent().hasExtra("Cardrarity") && getIntent().hasExtra("Cardtype") && getIntent().hasExtra("Cardset") && getIntent().hasExtra("Cardtext") && getIntent().hasExtra("Cardid")){
            String cardName = getIntent().getStringExtra("Cardname");
            String cardClass = getIntent().getStringExtra("Cardclass");
            int cardCost = getIntent().getIntExtra("Cardcost", 0);
            String cardRarity = getIntent().getStringExtra("Cardrarity");
            String cardType = getIntent().getStringExtra("Cardtype");
            String cardSet = getIntent().getStringExtra("Cardset");
            String cardCollectible = getIntent().getStringExtra("Cardcollectible");
            String cardText = getIntent().getStringExtra("Cardtext");
            String cardId = getIntent().getStringExtra("Cardid");
            setCard(cardName, cardClass, cardCost, cardRarity, cardType, cardSet, cardCollectible, cardText, cardId);
        }
    }

    private void setCard(String cardName, String cardClass, int cardCost, String cardRarity, String cardType, String cardSet, String cardCollectible, String cardText, String cardId){
        TextView name = findViewById(R.id.CardName);
        name.setText(cardName);
        TextView Class = findViewById(R.id.CardClass);
        Class.setText("From the class"+ cardClass);
        TextView cost = findViewById(R.id.CardCost);
        cost.setText(" " + cardCost);
        TextView rarity = findViewById(R.id.CardRarity);
        rarity.setText("It is " + cardRarity);
        TextView type = findViewById(R.id.CardType);
        type.setText("It's a " + cardType);
        TextView set = findViewById(R.id.CardSet);
        set.setText("Fom the set " + cardSet);
        TextView collectible = findViewById(R.id.CardCollectible);
        collectible.setText(cardCollectible);
        TextView text = findViewById(R.id.CardText);
        text.setText(cardText);
        ImageView id = findViewById(R.id.CardId);
        Picasso.get().load("https://art.hearthstonejson.com/v1/256x/"+cardId+".jpg").into(id);
    }
}