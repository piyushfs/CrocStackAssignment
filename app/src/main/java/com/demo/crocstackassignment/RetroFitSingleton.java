package com.demo.crocstackassignment;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by acer on 4/5/2018.
 */

public class RetroFitSingleton {

    public static final String API_KEY="c750173e8cf7e5fdc2c331cf897ee8c3";
    public static final String BASE_URL="https://developers.zomato.com/api/v2.1/";

    public interface PostService
    {



        @Headers("user-key:"+API_KEY)
        @GET("search")
        Call<Restaurants> getRestaurants(@QueryMap Map<String,String> map);

        @Headers("user-key:"+API_KEY)
        @GET("cuisines")
        Call<Cuisines> getCuisines(@Query(value = "lat",encoded = false) String lat,@Query(value = "lon",encoded = false) String lon);



    }

    public static PostService postService=null;
    public static PostService getInstance()
    {
        if(postService==null) {
            Retrofit retroFit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retroFit.create(PostService.class);
        }
        return postService;
    }
}
