package com.demo.crocstackassignment;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/6/2018.
 */

public class Restaurants_ {

    @SerializedName("restaurant")
    @Expose
    private RestaurantInfo restaurantInfo;

    public Restaurants_(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

}
