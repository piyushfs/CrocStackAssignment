package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/5/2018.
 */

public class RestaurantAddress {

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("locality")
    @Expose
    private String locality;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    public RestaurantAddress(String address, String locality, String city, String zipcode) {
        this.address = address;
        this.locality = locality;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
