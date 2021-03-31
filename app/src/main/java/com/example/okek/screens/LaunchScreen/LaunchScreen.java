package com.example.okek.screens.LaunchScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.okek.databinding.ActivityLaunchScreenBinding;
import com.example.okek.screens.SignInScreen.SignInScreen;
import com.example.okek.screens.SignUpScreen.SignUpScreen;

public class LaunchScreen extends AppCompatActivity {
    Intent intent;
    ActivityLaunchScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLaunchScreenBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("firstLoaded", Context.MODE_PRIVATE);

        boolean firstLoaded = sharedPreferences.getBoolean("firstLoaded",true);
        if (firstLoaded)
        {
            SharedPreferences.Editor e = sharedPreferences.edit();
            e.putBoolean("firstLoaded",false);
            e.apply();
            intent = new Intent(LaunchScreen.this, SignInScreen.class);
        }
        else intent = new Intent(LaunchScreen.this, SignUpScreen.class);

        new Handler().postAtTime(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
        }},2000);


    }
}