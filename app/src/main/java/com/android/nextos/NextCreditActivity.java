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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NextCreditActivity extends AppCompatActivity {
    NextcreditBinding binding;
    String url = NextAboutActivity.ServerLink+NextAboutActivity.BUILD+"/json/contributers.json";

    ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    StringRequest request = new StringRequest(url.replace("main" , NextAboutActivity.BUILD), new Response.Listener<String>() {
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
                    UserModel userModel = new UserModel(name, photo, summery, link);
                    userModelArrayList.add(userModel);

                }
                UserAdapter userAdapter = new UserAdapter(userModelArrayList, NextCreditActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NextCreditActivity.this);
                binding.nextCredit.setLayoutManager(linearLayoutManager);
                binding.nextCredit.setAdapter(userAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }, error -> Toast.makeText(NextCreditActivity.this, "CHECK INTERNET", Toast.LENGTH_LONG).show());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NextcreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView title = findViewById(R.id.topbar_title);
        title.setText(R.string.credit_s);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        ImageView xBack = findViewById(R.id.back_button);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

}