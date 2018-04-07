package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/7/2018.
 */

public class Cuisines_ {

    @SerializedName("cuisine")
    @Expose
    private CuisineInfo cuisine;

    public Cuisines_(CuisineInfo cuisine) {
        this.cuisine = cuisine;
    }

    public CuisineInfo getCuisine() {
        return cuisine;
    }
}
