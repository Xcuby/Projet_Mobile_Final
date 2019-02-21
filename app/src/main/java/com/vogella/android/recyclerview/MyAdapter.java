package com.vogella.android.recyclerview;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.CardActivity;
import xavier.albanet.projetprogrammationmobile.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Card> values;
    private Context myContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, List<Card> myDataset) {
        values = myDataset;
        myContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Card currentCard = values.get(position);
        final String name = currentCard.getName();
        final String cardclass = currentCard.getCardClass();
        final int cost = currentCard.getCost();
        final String rarity = currentCard.getRarity();
        final String type = currentCard.getType();
        final String set = currentCard.getSet();
        final boolean collectible = currentCard.isCollectible();
        final String text = currentCard.getText();
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(myContext, CardActivity.class);
                intent.putExtra("Cardname", name);
                intent.putExtra("Cardclass", cardclass);
                intent.putExtra("Cardcost", cost);
                intent.putExtra("Cardrarity", rarity);
                intent.putExtra("Cardtype", type);
                intent.putExtra("Cardset", set);
                intent.putExtra("Cardcollectible", collectible);
                intent.putExtra("Cardtext", text);
                myContext.startActivity(intent);
            }
        });
        holder.txtFooter.setText(rarity);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}