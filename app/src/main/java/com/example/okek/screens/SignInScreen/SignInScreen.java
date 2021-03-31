package com.example.okek.screens.SignInScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.okek.common.CheckData;
import com.example.okek.databinding.ActivitySignInBinding;
import com.example.okek.screens.SignUpScreen.SignUpScreen;

public class SignInScreen extends AppCompatActivity {

    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });
    }

    public void SignIn(View view) {
        if(
                        !binding.emailET.getText().toString().isEmpty()&&
                        !binding.passwordET.getText().toString().isEmpty()
        )
        {
            if (CheckData.checkMail(binding.emailET.getText().toString())){
                CheckData.authConfirmed(SignInScreen.this,
                        binding.emailET.getText().toString(),
                        binding.passwordET.getText().toString());
            }
            else {
                CheckData.makeMessage("Некорректный e-mail",this);
            }
        }
        else {
            CheckData.makeMessage("Есть пустые поля",this);
        }

    }

    public void goSignUp(View view) {
        Intent signUp=new Intent(SignInScreen.this,SignUpScreen.class);
        startActivity(signUp);
        finish();
    }
}