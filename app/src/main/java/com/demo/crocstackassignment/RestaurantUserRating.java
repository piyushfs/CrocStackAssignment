package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/5/2018.
 */

public class RestaurantUserRating {

    @SerializedName("aggregate_rating")
    @Expose
    private String aggregateRating;

    public RestaurantUserRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getAggregateRating() {
        return aggregateRating;
    }
}
