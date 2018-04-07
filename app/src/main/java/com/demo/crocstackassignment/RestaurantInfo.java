package com.demo.crocstackassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 4/5/2018.
 */

public class RestaurantInfo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("location")
    @Expose
    private RestaurantAddress location;

    @SerializedName("average_cost_for_two")
    @Expose
    private Integer average_cost_for_two;

    @SerializedName("user_rating")
    @Expose
    private RestaurantUserRating user_rating;

    @SerializedName("has_online_delivery")
    @Expose
    private Integer has_online_delivery;

    @SerializedName("has_table_booking")
    @Expose
    private Integer has_table_booking;

    @SerializedName("deeplink")
    @Expose
    private String deeplink;

    @SerializedName("cuisines")
    @Expose
    private String cuisines;

    public RestaurantInfo(String id, String name, String url, RestaurantAddress location, Integer average_cost_for_two
            , RestaurantUserRating user_rating, Integer has_online_delivery, Integer has_table_booking, String deeplink, String cuisines) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.location = location;
        this.average_cost_for_two = average_cost_for_two;
        this.user_rating = user_rating;
        this.has_online_delivery = has_online_delivery;
        this.has_table_booking = has_table_booking;
        this.deeplink = deeplink;
        this.cuisines = cuisines;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public RestaurantAddress getLocation() {
        return location;
    }

    public Integer getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public RestaurantUserRating getUser_rating() {
        return user_rating;
    }

    public Integer getHas_online_delivery() {
        return has_online_delivery;
    }

    public Integer getHas_table_booking() {
        return has_table_booking;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public String getCuisines() {
        return cuisines;
    }
}
