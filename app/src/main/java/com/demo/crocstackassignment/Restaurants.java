package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by acer on 4/5/2018.
 */

public class Restaurants {

    @SerializedName("results_found")
    @Expose
    private String resultsFound;
    @SerializedName("results_start")
    @Expose
    private String resultsStart;
    @SerializedName("results_shown")
    @Expose
    private String resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurants_> restaurants = null;

    public Restaurants(String resultsFound, String resultsStart, String resultsShown,
                        List<Restaurants_> restaurants) {
        this.resultsFound = resultsFound;
        this.resultsStart = resultsStart;
        this.resultsShown = resultsShown;
        this.restaurants = restaurants;
    }

    public String getResultsFound() {
        return resultsFound;
    }

    public String getResultsStart() {
        return resultsStart;
    }

    public String getResultsShown() {
        return resultsShown;
    }

    public List<Restaurants_> getRestaurants() {
        return restaurants;
    }
}
