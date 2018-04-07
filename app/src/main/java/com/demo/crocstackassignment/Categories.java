package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/5/2018.
 */

public class Categories {

   // @SerializedName("id")
   // @Expose
   // private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

   /* public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
