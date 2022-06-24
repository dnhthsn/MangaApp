package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.fragment.FragmentHome;
import com.example.mangaapp.fragment.FragmentInfo;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final int FRAGMENT_HOME_INDEX = 0;
    private final int FRAGMENT_INFO_INDEX = 1;
    private int SELECT_IMAGE_CODE = 1;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private NavigationView mNavigationView;
    private TextView nameHeader;
    private CircleImageView imageHeader;
    private View header;

    private int currentFragment = FRAGMENT_HOME_INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolBar = findViewById(R.id.tool_bar);
        mNavigationView = findViewById(R.id.navigation_view);

        header = mNavigationView.getHeaderView(0);
        nameHeader = header.findViewById(R.id.name_header);
        imageHeader = header.findViewById(R.id.img_header);

        imageHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });

        if (!TextUtils.isEmpty(Prevalent.currentOnlineUser.getName())) {
            nameHeader.setText(Prevalent.currentOnlineUser.getName());
        }

        setSupportActionBar(mToolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        ReplaceFragment(new FragmentHome());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (currentFragment != FRAGMENT_HOME_INDEX) {
                ReplaceFragment(new FragmentHome());
                currentFragment = FRAGMENT_HOME_INDEX;
            }
        } else if (id == R.id.nav_info) {
            if (currentFragment != FRAGMENT_INFO_INDEX) {
                ReplaceFragment(new FragmentInfo());
                currentFragment = FRAGMENT_INFO_INDEX;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            imageHeader.setImageURI(uri);
        }
    }

    private void ReplaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}