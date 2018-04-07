package com.demo.crocstackassignment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private double mLatitude=19.098420, mLongitude=72.865381;
    private RecyclerView mRecyclerView;
    private HotelListAdapter mHotelListAdapter;
    private List<Restaurants_> mRestaurantList;
    private ProgressBar mProgressBar1,mProgressBar2;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mShouldScroll=false;
    private int mTotalResults,mFetchedResults,mOffset;
    private String mRadius="5000";
    private EditText mLocation,mHotelName;
    private String location,hotelname;
    private Map<String,String> mMap;
    private List<Cuisines_> mCuisines;
    private int mSortByRating=-1;
    private int mSortByCash=-1;
    private Button mCost,mRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check Location Permissions
        checkPermissions();

        //initialize
        mMap=new HashMap<>();
        mHotelName=(EditText)findViewById(R.id.edit_hotel_name);
        mLocation=(EditText)findViewById(R.id.edit_location);
        mLocation.setOnKeyListener(null);
        mCost=(Button)findViewById(R.id.btn_cost);
        mRating=(Button)findViewById(R.id.btn_rating);
        mProgressBar1=(ProgressBar)findViewById(R.id.progress_bar1);
        mProgressBar2=(ProgressBar)findViewById(R.id.progress_bar2);
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        mProgressBar2.setVisibility(View.GONE);

        mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //show Initial Restaurant List
        mMap.put("lat",Double.toString(mLatitude));
        mMap.put("lon",Double.toString(mLongitude));
        mMap.put("radius",mRadius);
        showRestaurants();

        //implement pagination
        pagination();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK)
        {
            Place place=PlacePicker.getPlace(this,data);
            mLocation.setText(place.getAddress());
            mLatitude=place.getLatLng().latitude;
            mLongitude=place.getLatLng().longitude;
        }
    }

    public void openPlacePicker(View view) {
        PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
        try {
            Intent intent=builder.build(this);
            startActivityForResult(intent,100);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    public void searchBtn(View view) {
            mProgressBar1.setVisibility(View.VISIBLE);
            location=mLocation.getText().toString();
            hotelname=mHotelName.getText().toString();
            if(mSortByCash!=-1)
            {
                mSortByCash=-1;
                mCost.setText(R.string.cost);
            }
            if(mSortByRating!=-1)
            {
                mSortByRating=-1;
                mRating.setText(R.string.rating);
            }

        if(!location.trim().isEmpty() && !hotelname.trim().isEmpty())
        {
            mMap.clear();
            mMap.put("q",hotelname);
            mMap.put("lat",Double.toString(mLatitude));
            mMap.put("lon",Double.toString(mLongitude));
            mMap.put("radius",mRadius);
            mMap.put("sort","real_distance");
            mMap.put("order","asc");
            updateRestaurants();

        }
        else if(location.trim().isEmpty() && !hotelname.trim().isEmpty())
        {
            mMap.clear();
            mMap.put("q",hotelname);
            updateRestaurants();

        }
        else if(!location.trim().isEmpty() && hotelname.trim().isEmpty())
        {
            mMap.clear();
            mMap.put("lat",Double.toString(mLatitude));
            mMap.put("lon",Double.toString(mLongitude));
            mMap.put("radius",mRadius);
            mMap.put("sort","real_distance");
            mMap.put("order","asc");
            updateRestaurants();

        }
        else
        {
            mProgressBar1.setVisibility(View.GONE);
        }

    }

    public void sortByRating(View view) {
        mProgressBar1.setVisibility(View.VISIBLE);
        mMap.remove("sort");
        mMap.remove("order");
        mMap.put("sort","rating");
        if(mSortByCash!=-1)
        {
            mSortByCash=-1;
            mCost.setText(R.string.cost);
        }
        if(mSortByRating==-1 || mSortByRating==1)
        {
            mSortByRating=0;
            mRating.setText(R.string.rating_asc);
            mMap.put("order","asc");
        }
        else
        {
            mSortByRating=1;
            mRating.setText(R.string.rating_desc);
            mMap.put("order","desc");
        }
        updateRestaurants();

    }

    public void sortByCost(View view) {
        mProgressBar1.setVisibility(View.VISIBLE);
        mMap.remove("sort");
        mMap.remove("order");
        mMap.put("sort","cost");
        if(mSortByRating!=-1)
        {
            mSortByRating=-1;
            mRating.setText(R.string.rating);
        }
        if(mSortByCash==-1 || mSortByCash==1)
        {
            mSortByCash=0;
            mCost.setText(R.string.cost_asc);
            mMap.put("order","asc");
        }
        else
        {
            mSortByCash=1;
            mCost.setText(R.string.cost_desc);
            mMap.put("order","desc");
        }
        updateRestaurants();
    }

    public void sortByCuisines(View view) {

        getCuisines();

    }

    protected void checkPermissions()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);

        }
    }

    protected void showRestaurants()
    {
            Toast.makeText(this,"Showing restaurants from default location",Toast.LENGTH_LONG).show();
        Call<Restaurants> list = RetroFitSingleton.getInstance().getRestaurants(mMap);
        list.enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                if(response.code()==200)
                {
                    Log.i("check",response.toString());
                    mTotalResults=Integer.parseInt(response.body().getResultsFound());
                    mFetchedResults=Integer.parseInt(response.body().getResultsShown());
                    mOffset=Integer.parseInt(response.body().getResultsStart());
                    mRestaurantList=response.body().getRestaurants();
                    mHotelListAdapter=new HotelListAdapter(MainActivity.this,mRestaurantList);
                    mRecyclerView.setAdapter(mHotelListAdapter);
                    mProgressBar1.setVisibility(View.GONE);

                }


            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Check Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void getCuisines()
    {
        Call<Cuisines> list = RetroFitSingleton.getInstance().getCuisines(Double.toString(mLatitude),Double.toString(mLongitude));
        list.enqueue(new Callback<Cuisines>() {
            @Override
            public void onResponse(Call<Cuisines> call, Response<Cuisines> response) {
                Log.i("check",response.toString());
                if(response.code()==200)
                {
                    mCuisines=response.body().getCuisines();
                    mProgressBar1.setVisibility(View.GONE);
                    final CharSequence[] items = new CharSequence[mCuisines.size()];
                    for (int i = 0; i < mCuisines.size(); i++) {
                        items[i] = mCuisines.get(i).getCuisine().getCuisineName();
                    }
                    final ArrayList<Integer> seletedItems = new ArrayList();
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Select Cuisine")
                            .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                    if (isChecked) {
                                        seletedItems.add(mCuisines.get(indexSelected).getCuisine().getCuisineId());
                                    } else if (seletedItems.contains(mCuisines.get(indexSelected).getCuisine().getCuisineId())) {
                                        seletedItems.remove(mCuisines.get(indexSelected).getCuisine().getCuisineId());
                                    }
                                }
                            }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    String res = "";
                                    mMap.remove("cuisines");
                                    for (int i = 0; i < seletedItems.size() - 1; i++) {
                                        res = res.concat(Integer.toString(seletedItems.get(i)) + ",");
                                    }
                                    res = res.concat(Integer.toString(seletedItems.get(seletedItems.size() - 1)));
                                    mMap.put("cuisines", res);
                                    updateRestaurants();


                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    seletedItems.clear();
                                }
                            }).create();
                    dialog.show();
                }

            }

            @Override
            public void onFailure(Call<Cuisines> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                mProgressBar2.setVisibility(View.GONE);
            }
        });
    }

    protected void pagination()
    {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_DRAGGING)
                {
                    mShouldScroll=true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total=mLinearLayoutManager.getItemCount();
                int visible=mLinearLayoutManager.findFirstVisibleItemPosition();
                int current=mLinearLayoutManager.getChildCount();
                if(mShouldScroll && total==(visible+current) && mTotalResults > (mOffset+mFetchedResults))
                {
                    mShouldScroll=false;
                    mOffset=mOffset+mFetchedResults;
                    mProgressBar2.setVisibility(View.VISIBLE);
                    Log.i("check","total"+mTotalResults+" offset"+mOffset+" fetched"+mFetchedResults);
                    Log.i("check", "total" + total + " first" + visible + " curr" + current);
                    mMap.put("start",Integer.toString(mOffset+1));
                    Call<Restaurants> list = RetroFitSingleton.getInstance().getRestaurants(mMap);
                    list.enqueue(new Callback<Restaurants>() {
                        @Override
                        public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                            if(response.code()==200)
                            {
                                mRestaurantList=response.body().getRestaurants();
                                mHotelListAdapter.addToList(mRestaurantList);
                                mHotelListAdapter.notifyDataSetChanged();
                                mProgressBar2.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<Restaurants> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                            mProgressBar2.setVisibility(View.GONE);
                        }
                    });
                }

            }
        });
    }

    protected void updateRestaurants()
    {
            Call<Restaurants> list = RetroFitSingleton.getInstance().getRestaurants(mMap);
            list.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    if(response.code()==200)
                    {
                        Log.i("check",response.toString());
                        mTotalResults=Integer.parseInt(response.body().getResultsFound());
                        mFetchedResults=Integer.parseInt(response.body().getResultsShown());
                        mOffset=Integer.parseInt(response.body().getResultsStart());
                        mRestaurantList=response.body().getRestaurants();
                        mHotelListAdapter.clearAndAddToList(mRestaurantList);
                        mHotelListAdapter.notifyDataSetChanged();
                        mProgressBar1.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                    mProgressBar2.setVisibility(View.GONE);
                }
            });

    }

}
