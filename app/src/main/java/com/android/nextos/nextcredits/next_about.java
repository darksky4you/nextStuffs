package com.android.nextos.nextcredits;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.nextos.R;

public class next_about extends AppCompatActivity implements View.OnClickListener {
    CardView xNextDetails, xMaintainer, xCommunity, xTeam, xContribute, xSupportgroup;
    ImageView xBack;
    private final String isOfficial = getSystemProperty("ro.next.official");
    String URL;
    TextView title, isGenuine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_about_main);
        xBack = findViewById(R.id.back_button);
        xNextDetails = findViewById(R.id.nextos_details);
        xMaintainer = findViewById(R.id.maintainer);
        xCommunity = findViewById(R.id.community);
        xTeam = findViewById(R.id.team_members);
        xContribute = findViewById(R.id.contributes);
        xSupportgroup = findViewById(R.id.support_g);

        xBack.setOnClickListener(this);
        xNextDetails.setOnClickListener(this);
        xMaintainer.setOnClickListener(this);
        xCommunity.setOnClickListener(this);
        xTeam.setOnClickListener(this);
        xContribute.setOnClickListener(this);
        xSupportgroup.setOnClickListener(this);
        isGenuine = findViewById(R.id.build_status);

        if (isOfficial.equals("true")) {
            isGenuine.setText("OFFICIAL");


        } else {
            isGenuine.setText("UNOFFICIAL");
        }


        title = findViewById(R.id.topbar_title);
        title.setText("About Project");


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


    @Override
    public void onClick(View view) {
        int xid = view.getId();
        if (xid == R.id.nextos_details) {
            URL = "https://github.com/darksky4you/NEXT_PARTICLE_PROJECT/";
            OpenLink(URL);

        } else if (xid == R.id.maintainer) {
            URL = "https://t.me/darksky4you";
            OpenLink(URL);

        } else if (xid == R.id.community) {
            URL = "https://t.me/RedmiN11Pak";
            OpenLink(URL);

        } else if (xid == R.id.team_members) {
            Intent intent = new Intent(next_about.this, nextmembers.class);
            startActivity(intent);

        } else if (xid == R.id.contributes) {
            Intent intent = new Intent(next_about.this, nextcredit.class);
            startActivity(intent);

        } else if (xid == R.id.support_g) {
            URL = "https://t.me/nextosmerlinx";
            OpenLink(URL);

        }


    }


    public String getSystemProperty(String key) {
        String value = null;

        try {
            value = (String) Class.forName("android.os.SystemProperties")
                    .getMethod("get", String.class).invoke(null, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


    void OpenLink(String Url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
        startActivity(browserIntent);

    }
}