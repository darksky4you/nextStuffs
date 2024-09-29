package com.android.nextos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class NextAboutActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_LINK = "https://raw.githubusercontent.com/Fazokhan/NEXTOS_PROJECT/main/database/";
    public static final String NEXTOS_CODENAME = getPropValue("ro.nextos.codename");
    private CardView xNextDetails, xMaintainer, xCommunity, xTeam, xContribute, xSupportgroup, xGithubLink, xSponsors;
    private ImageView xBack;
    private TextView title, isGenuine, codenameText, DevName, DevStatus, supportGroupName;
    private String CommunityLink = "https://t.me/";
    private String mUserName, mSuUserName;
    public static Boolean IS_OFFICIAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_about_main);
        initializeViews();
        setClickListeners();
        updateBuildType();
        setUpTitleAndCodename();
        configurePort();
    }

    // Initialize views with better grouping for readability
    private void initializeViews() {
        // ImageView
        xBack = findViewById(R.id.back_button);

        // CardViews
        xNextDetails = findViewById(R.id.nextos_details);
        xMaintainer = findViewById(R.id.maintainer);
        xCommunity = findViewById(R.id.community);
        xTeam = findViewById(R.id.team_members);
        xContribute = findViewById(R.id.contributes);
        xSupportgroup = findViewById(R.id.support_g);
        xSponsors = findViewById(R.id.sponsers);
        xGithubLink = findViewById(R.id.git_post);

        // TextViews
        isGenuine = findViewById(R.id.build_status);
        title = findViewById(R.id.topbar_title);
        codenameText = findViewById(R.id.codename_text);
        DevName = findViewById(R.id.DevName);
        DevStatus = findViewById(R.id.DevStatus);
        supportGroupName = findViewById(R.id.supportGroupName);
    }

    private void setClickListeners() {
        // Attach click listeners in a concise manner
        View[] clickableViews = {xBack, xNextDetails, xMaintainer, xCommunity, xTeam, xContribute, xSupportgroup, xSponsors, xGithubLink};
        for (View view : clickableViews) {
            view.setOnClickListener(this);
        }
    }

    // Check if the build is official by comparing the codename to the supported list
    private void updateBuildType() {
        String deviceCodename = getPropValue("ro.product.vendor.name");
        String[] supportedDevices = getResources().getStringArray(R.array.official_supported_devices);

        IS_OFFICIAL = false;
        for (String supportedDevice : supportedDevices) {
            if (supportedDevice.equals(deviceCodename)) {
                IS_OFFICIAL = true;
                break;
            }
        }
    }

    // Update maintainer and support group details
    private void updateMaintainerStatus() {
        String maintainerTitle = getPropValue("ro.nextos.maintainer.title");
        mUserName = getPropValue("ro.nextos.maintainer.username");

        String supportTitle = getPropValue("ro.nextos.support.title");
        mSuUserName = getPropValue("ro.nextos.support.username");

        // Ensure non-null values to avoid runtime issues
        DevName.setText(maintainerTitle != null ? maintainerTitle : "Unknown Maintainer");
        supportGroupName.setText(supportTitle != null ? supportTitle : "Unknown Support");
    }

    // Configure the port or official status dynamically
    private void configurePort() {
        if (IS_OFFICIAL) {
            isGenuine.setText(R.string.status_official);  // Official build
            DevStatus.setText("#MAINTAINER");
            xGithubLink.setVisibility(View.VISIBLE);
        } else {
            isGenuine.setText(R.string.status_unofficial);  // Unofficial port
            DevStatus.setText("#PORTER");
            xGithubLink.setVisibility(View.GONE);
        }
        updateMaintainerStatus();
        isGenuine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IS_OFFICIAL){
                    Toast.makeText(NextAboutActivity.this, "This Device is Officially Supported by NEXT-OS", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(NextAboutActivity.this, "The Rom is Ported for this Device", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    // Setup the title and codename with a fallback
    private void setUpTitleAndCodename() {
        title.setText(R.string.about_project_title);  // Set the title
        codenameText.setText(NEXTOS_CODENAME != null ? NEXTOS_CODENAME.toUpperCase() : "UNKNOWN");
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.back_button) {
            onBackPressed();
        } else if (viewId == R.id.nextos_details) {
            openActivity(NextDetailsActivity.class);
        } else if (viewId == R.id.maintainer) {
            openLink(CommunityLink + mUserName);
        } else if (viewId == R.id.community) {
            openLink(R.string.community_link_xd);
        } else if (viewId == R.id.team_members) {
            openActivity(NextMembersActivity.class);
        } else if (viewId == R.id.contributes) {
            openActivity(NextCreditActivity.class);
        } else if (viewId == R.id.sponsers) {
            openActivity(NextSponsorsActivity.class);
        } else if (viewId == R.id.support_g) {
            openLink(CommunityLink + mSuUserName);
        } else if (viewId == R.id.git_post) {
            openLink(getString(R.string.project_details_link_xd) + NEXTOS_CODENAME);
        }
    }


    // Helper method to start a new activity
    private void openActivity(Class<?> activityClass) {
        startActivity(new Intent(NextAboutActivity.this, activityClass));
    }

    // Helper method to open a URL via an intent
    private void openLink(int urlResourceId) {
        String url = getString(urlResourceId);
        openLink(url);
    }

    private void openLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    // Fetch system property value safely with fallback
    public static String getPropValue(String key) {
        try {
            return (String) Class.forName("android.os.SystemProperties")
                    .getMethod("get", String.class)
                    .invoke(null, key);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";  // Return a safe fallback value
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Default back press behavior
    }
}
