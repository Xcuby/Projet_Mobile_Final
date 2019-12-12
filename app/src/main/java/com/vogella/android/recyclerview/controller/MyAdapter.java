package com.vogella.android.recyclerview.controller;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.CardActivity;

import java.util.ArrayList;
import java.util.List;

import xavier.albanet.projetprogrammationmobile.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    private List<Card> values;
    private List<Card> valuesFiltered;
    private Context myContext;
    private final OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Card card);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imgIcon;
        public View layout;
        public ImageView imgFavorite;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imgIcon = (ImageView) v.findViewById(R.id.icon);
            imgFavorite =  v.findViewById(R.id.image_favorite);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, List<Card> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.myContext = context;
        this.listener = listener;
        this.valuesFiltered = myDataset;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Card currentCard = valuesFiltered.get(position);
        final String name = currentCard.getName();
        final String cardclass = currentCard.getCardClass();
        final int cost = currentCard.getCost();
        final String rarity = currentCard.getRarity();
        final String type = currentCard.getType();
        final String set = currentCard.getSet();
        final String collectible = currentCard.isCollectible();
        final String text = currentCard.getText();
        final String id = currentCard.getId();
        holder.txtHeader.setText(name);
        holder.txtFooter.setText(text);
        holder.imgIcon.setAnimation(AnimationUtils.loadAnimation(myContext, R.anim.fade_transition_animation));
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(myContext, R.anim.fade_scale_animation));
        Picasso.get().load("https://art.hearthstonejson.com/v1/256x/" + id + ".jpg").into(holder.imgIcon);
        holder.itemView.setOnClickListener(new OnClickListener() {
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
                intent.putExtra("Cardid", id);
                myContext.startActivity(intent);
            }
        });

        holder.imgFavorite.setSelected(currentCard.isFav());

        holder.imgFavorite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgFavorite.setSelected(!holder.imgFavorite.isSelected());
                listener.onItemClick(currentCard);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return valuesFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    valuesFiltered = values;
                } else {
                    List<Card> lisFiltered = new ArrayList<Card>();
                    for (Card row : values) {
                        if (row.getName().toLowerCase().contains(Key.toLowerCase())){
                            lisFiltered.add(row);
                        }
                    }
                    valuesFiltered = lisFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = valuesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                valuesFiltered = (List<Card>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}