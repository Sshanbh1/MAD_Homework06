package com.example.hw06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.sql.Savepoint;

public class MainActivity extends AppCompatActivity implements  MyProfileFragment.OnFragmentInteractionListener, SelectAvatar.onSelectAvatar, DisplayMyProfile.onDisplayProfile {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mPrefs = getSharedPreferences("Profile", MODE_PRIVATE);
        String json = mPrefs.getString("profileDetails", "");

        if(json == ""){
            setTitle("My Profile");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MyProfileFragment(), "myProfileFrag")
                    .commit();
        } else {
            setTitle("Display My Profile");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new DisplayMyProfile(), "displayMyProfile")
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction() {
        setTitle("Display My Profile");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DisplayMyProfile(), "displayMyProfile")
                .commit();
    }

    @Override
    public void onDisplayProfile() {
        setTitle("My Profile");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MyProfileFragment(), "myProfileFrag")
                .commit();
    }

    @Override
    public void onSelectAvatar() {
        setTitle("My Profile");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MyProfileFragment(), "myProfileFrag")
                .commit();
    }
}
