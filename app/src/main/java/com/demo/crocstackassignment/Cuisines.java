package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by acer on 4/7/2018.
 */

public class Cuisines {

    @SerializedName("cuisines")
    @Expose
    private List<Cuisines_> cuisines = null;

    public Cuisines(List<Cuisines_> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Cuisines_> getCuisines() {
        return cuisines;
    }
}
