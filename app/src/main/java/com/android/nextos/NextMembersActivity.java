package com.android.nextos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nextos.databinding.NextmembersBinding;

import java.util.ArrayList;

public class NextMembersActivity extends AppCompatActivity {
    private NextmembersBinding binding;
    private ArrayList<UserModel> userModelArrayList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NextmembersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        populateData();
    }

    private void initViews() {
        TextView title = findViewById(R.id.topbar_title);
        title.setText(R.string.members_s);

        ImageView xBack = findViewById(R.id.back_button);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        userModelArrayList = new ArrayList<>();
        userAdapter = new UserAdapter(userModelArrayList, this);
        binding.nextMembers.setLayoutManager(new LinearLayoutManager(this));
        binding.nextMembers.setAdapter(userAdapter);
    }

    private void populateData() {
        userModelArrayList.add(new UserModel("FAZO KHAN", "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/assets/members/fazokhan.jpg", "Owner and Senior", "https://t.me/Fazokhan"));
        userModelArrayList.add(new UserModel("SIAM (NIBBA)", "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/assets/members/siam.jpg", "Pro Porter and Helper", "https://t.me/NebbaGiveUp"));
        userModelArrayList.add(new UserModel("GUFFY", "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/assets/members/guffy.jpg", "Alioth Dev", "https://t.me/GUFFYWa"));
        userModelArrayList.add(new UserModel("ISHAN DEV", "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/assets/members/ishan.jpg", "Remix Dev", "https://t.me/krishna_prem_is_lob"));
        userModelArrayList.add(new UserModel("OBITO XD", "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/assets/members/obito.jpg", "A Human Being", "https://t.me/darksky4you"));


        // Notify adapter about data changes
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
