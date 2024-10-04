package com.android.nextos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.nextos.databinding.NextcreditBinding;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NextCreditActivity extends AppCompatActivity {
    private NextcreditBinding binding;
    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    private String url = NextAboutActivity.SERVER_LINK + NextAboutActivity.NEXTOS_CODENAME + "/json/contributers.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NextcreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the title of the top bar
        TextView title = findViewById(R.id.topbar_title);
        title.setText(R.string.credit_s);

        // Initialize the back button
        ImageView xBack = findViewById(R.id.back_button);
        xBack.setOnClickListener(view -> onBackPressed());

        // Fetch contributors data
        fetchContributors();
    }

    private void fetchContributors() {
        RequestQueue queue = Volley.newRequestQueue(this);

        // Create a StringRequest to fetch data from the server
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("credits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject singleObject = jsonArray.getJSONObject(i);
                                String name = singleObject.getString("name");
                                String photo = singleObject.getString("photo");
                                String summery = singleObject.getString("summery");
                                String link = singleObject.getString("link");

                                // Create a UserModel object and add it to the list
                                UserModel userModel = new UserModel(name, photo, summery, link);
                                userModelArrayList.add(userModel);
                            }

                            // Set up the RecyclerView with the adapter
                            UserAdapter userAdapter = new UserAdapter(userModelArrayList, NextCreditActivity.this);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(NextCreditActivity.this);
                            binding.nextCredit.setLayoutManager(layoutManager);
                            binding.nextCredit.setAdapter(userAdapter);

                        } catch (JSONException e) {
                            // Handle JSON parsing errors
                            Toast.makeText(NextCreditActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle network errors
                        Toast.makeText(NextCreditActivity.this, "Failed to fetch data. Check your internet connection.", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }
        );

        // Add the request to the queue
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
