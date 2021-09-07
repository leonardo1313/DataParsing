package com.optimahorizonapps.dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url = "https://ww.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    String apiObjectUrl = "https://jsonplaceholder.typicode.com/todos/1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        TextView textView = findViewById(R.id.main_textView);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiObjectUrl, null,
                response -> {
                    try {
                        textView.setText(response.getString("title"));
                        Log.d("JSONObject", "onCreate: " + response.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.d("JSONObject", "onCreate: Failed!");
                });

        queue.add(jsonObjectRequest);

//        getJsonArrayRequest();

//        getString();
    }

    private void getJsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("JSON", "onCreateOK: " + jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                error -> {
            Log.d("JSON", "onCreate: Failed!");
                });

        queue.add(jsonArrayRequest);
    }

    private void getString() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // display the content of url
            Log.d("Main", "onCreate: " + response.substring(0, 500));

        }, error -> {
            Log.d("Google", "Error!");
        });

        // add the request to RequestQueue
        queue.add(stringRequest);
    }
}