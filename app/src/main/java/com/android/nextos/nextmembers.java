package com.android.nextos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nextos.databinding.NextmembersBinding;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class nextmembers extends AppCompatActivity {
    NextmembersBinding binding;
    String url = "https://raw.githubusercontent.com/darksky4you/NEXT_PARTICLE_PROJECT/main/DATABASE/next_members.json";

    ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    StringRequest request = new StringRequest(url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("members");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject singleObject = jsonArray.getJSONObject(i);
                    String name = singleObject.getString("name");
                    String photo = singleObject.getString("photo");
                    String summery = singleObject.getString("summery");
                    String link = singleObject.getString("link");
                    UserModel userModel = new UserModel(name, photo, summery, link);
                    userModelArrayList.add(userModel);

                }
                UserAdapter userAdapter = new UserAdapter(userModelArrayList, nextmembers.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(nextmembers.this);
                binding.nextMembers.setLayoutManager(linearLayoutManager);
                binding.nextMembers.setAdapter(userAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }, error -> Toast.makeText(nextmembers.this, "CHECK INTERNET", Toast.LENGTH_LONG).show());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NextmembersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView title = findViewById(R.id.topbar_title);
        title.setText(R.string.members_s);
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