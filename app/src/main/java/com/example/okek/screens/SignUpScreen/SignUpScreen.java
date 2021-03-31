package com.example.okek.screens.SignUpScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.okek.common.AppData;
import com.example.okek.common.CheckData;
import com.example.okek.common.URLs;
import com.example.okek.common.entity.User;
import com.example.okek.databinding.ActivitySignUpBinding;
import com.example.okek.screens.SignInScreen.SignInScreen;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpScreen extends AppCompatActivity {
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }

    public void SignUp(View view) {
        if(
                !binding.nameET.getText().toString().isEmpty()&&
                !binding.secondnameET.getText().toString().isEmpty()&&
                !binding.emailET.getText().toString().isEmpty()&&
                !binding.passwordET.getText().toString().isEmpty()
        )
        {
            if (CheckData.checkMail(binding.emailET.getText().toString()))
            {
                if (binding.passwordET.getText().toString().equals(binding.password2ET.getText()))
                {
                    JSONObject user = new JSONObject();
                    try {
                        user.put(User.EMAIL, binding.emailET.getText().toString());
                        user.put(User.PASSWORD, binding.emailET.getText().toString());
                        user.put(User.FIRST_NAME, binding.nameET.getText().toString());
                        user.put(User.LAST_NAME, binding.secondnameET.getText().toString());
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    JsonObjectRequest signUpRequest = new JsonObjectRequest(Request.Method.POST,
                            URLs.REGISTER, user, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            CheckData.authConfirmed(SignUpScreen.this,
                                    binding.emailET.getText().toString(),
                                    binding.passwordET.getText().toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            CheckData.makeMessage("Проблема с регистрацией", SignUpScreen.this);
                        }
                    });
                    AppData.getInstance(this).queue.add(signUpRequest);
                }
                else
                {
                    CheckData.makeMessage("Пароли не совпадают",this);
                }
            }
            else
            {
                CheckData.makeMessage("Некорректный e-mail",this);
            }
        }
        else
        {
            CheckData.makeMessage("Есть пустые поля",this);
        }
    }

    public void CanSign(View view) {
        Intent SignIn = new Intent(SignUpScreen.this, SignInScreen.class);
        startActivity(SignIn);
        finish();
    }
}