package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/7/2018.
 */

public class CuisineInfo {

    @SerializedName("cuisine_id")
    @Expose
    private Integer cuisineId;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;

    public Integer getCuisineId() {
        return cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public CuisineInfo(Integer cuisineId, String cuisineName) {

        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
    }
}
