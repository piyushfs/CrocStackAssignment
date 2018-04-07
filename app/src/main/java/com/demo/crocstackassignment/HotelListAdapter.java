package com.demo.crocstackassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by acer on 4/6/2018.
 */

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {

    private List<Restaurants_> mRestaurantsList;
    private Context mContext;

    public HotelListAdapter(Context mContext,List<Restaurants_> mRestaurantsList) {
        this.mRestaurantsList = mRestaurantsList;
        this.mContext = mContext;
    }



    @Override
    public HotelListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_restaurant_list_layout, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelListAdapter.ViewHolder holder, int position) {
            holder.rating.setText(mRestaurantsList.get(position).getRestaurantInfo().getUser_rating().getAggregateRating());
            holder.name.setText(mRestaurantsList.get(position).getRestaurantInfo().getName());
            holder.address.setText(mRestaurantsList.get(position).getRestaurantInfo().getLocation().getAddress());
            holder.cost.setText("Avg. cost for two: " + mRestaurantsList.get(position).getRestaurantInfo().getAverage_cost_for_two());
            holder.cuisines.setText(mRestaurantsList.get(position).getRestaurantInfo().getCuisines());
    }

    @Override
    public int getItemCount() {
        return mRestaurantsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,address,cuisines,cost,rating;
        public ViewHolder(View itemView) {

                super(itemView);

                name = (TextView) itemView.findViewById(R.id.text_restaurant_name);
                address = (TextView) itemView.findViewById(R.id.text_address);
                cuisines = (TextView) itemView.findViewById(R.id.text_cuisines);
                cost = (TextView) itemView.findViewById(R.id.text_cost);
                rating = (TextView) itemView.findViewById(R.id.text_rating);

        }
    }

    public void addToList(List<Restaurants_> list)
    {
        mRestaurantsList.addAll(list);
    }

    public void clearAndAddToList(List<Restaurants_> list)
    {
        mRestaurantsList.clear();
        mRestaurantsList.addAll(list);

    }

}
