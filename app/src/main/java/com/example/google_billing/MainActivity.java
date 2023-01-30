package com.example.google_billing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.google_billing.Database.DatabaseClient;
import com.example.google_billing.Database.room_data_modal;
import com.example.google_billing.Util.CheckInternetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view
    private ArrayList<room_data_modal> userModalArrayList;
   List<room_data_modal> medDta = new ArrayList<>();
    private UserRVAdapter userRVAdapter;
    private RecyclerView userRV;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;
   List<room_data_modal> quotlist=new ArrayList();

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 1, limit = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating a new array list.
        userModalArrayList = new ArrayList<>();

        // initializing our views.
        userRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        // calling a method to load our api.
       // getDataFromAPI(page, limit);
       // getMedData();

        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);

                    //getDataFromAPI(page, limit);
                    if (CheckInternetConnection.isConnectionAvailable(MainActivity.this)) {

                        Log.d("test1","test1");
                        getDataFromAPI(page, limit);

                    }else{
                        Log.d("test2","test2");
                        getMedData(page, limit);
                    }
                }
            }
        });

        if (CheckInternetConnection.isConnectionAvailable(MainActivity.this)) {

            Log.d("test1","test1");
            getDataFromAPI(page, limit);

        }else{
            Log.d("test2","test2");
            getMedData(page, limit);
        }

    }



    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }
        // creating a string variable for url .
        String url = "https://quotable.io/quotes?page=" + page;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // creating a variable for our json object request and passing our url to it.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RESPONSE", String.valueOf(response));
                try {

                    // on below line we are extracting data from our json array.
                    JSONArray dataArray = response.getJSONArray("results");

                    Log.d("RESPONSE", String.valueOf(dataArray));

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (DatabaseClient.getInstance(getApplicationContext())
                                        .getAppDatabase()
                                        .Quote_DAO().getQuotesCount(response.getInt("page")) > 0) {

                                } else {
                                    Log.d("test3","test3");

                                    // passing data from our json array in our array list.
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        JSONObject jsonObject = dataArray.getJSONObject(i);
                                        Log.d("RESPONSE", String.valueOf(jsonObject));


                                        DatabaseClient.getInstance(getApplicationContext())
                                                .getAppDatabase()
                                                .Quote_DAO()
                                                .insertQuote(new room_data_modal(response.getInt("page"),
                                                        jsonObject.getString("_id"), jsonObject.getString("author"),jsonObject.getString("content")
                                                ));



                                    }
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        ;
                    });



                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        Log.d("RESPONSE", String.valueOf(jsonObject));

                        // on below line we are extracting data from our json object.
                        userModalArrayList.add(new room_data_modal(response.getInt("page"),
                                jsonObject.getString("_id"), jsonObject.getString("author"),jsonObject.getString("content")
                        ));

                        // passing array list to our adapter class.
                        userRVAdapter = new UserRVAdapter(userModalArrayList, MainActivity.this);

                        // setting layout manager to our recycler view.
                        userRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        // setting adapter to our recycler view.
                        userRV.setAdapter(userRVAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling on error listener method.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
    }


    public void getMedData(int page,int limit) {
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }else {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                Room_medicine_modal.clear();


                medDta = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .Quote_DAO().getAllQuotes(page);
               // quotlist  += medDta;

                quotlist.addAll(medDta);
                Log.d("meddata", String.valueOf(quotlist));
                MainActivity.this.runOnUiThread(() -> {
                    Log.d("check3", "hi");
                    if(quotlist.size()>0) {
                        if(userRV != null) {
                            userRVAdapter = new UserRVAdapter(quotlist, MainActivity.this);
                            userRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            userRV.setAdapter(userRVAdapter);
                            userRVAdapter.notifyDataSetChanged();
                            loadingPB.setVisibility(View.GONE);
                        }

                    }


                });

            }
        });}
    }


}