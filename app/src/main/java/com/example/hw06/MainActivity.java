package com.example.hw06;

/*
*
* Name : Sameer Shanbhag
* Name : Ravina Gaikawad
* Group1 5
* MAD Homework 06
* Topic: Fragments | SharedPreferences
*
*/


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
