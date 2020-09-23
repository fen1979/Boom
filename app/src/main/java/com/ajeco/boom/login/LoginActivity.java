package com.ajeco.boom.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ajeco.boom.MainActivity;
import com.ajeco.boom.R;
import com.ajeco.boom.ui.JsonPlaceHolderAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private JsonPlaceHolderAPI jsphapi;
    private EditText userEmail, userPassword;
    private Button loginButton;
    private ProgressBar loadingProgressBar;
    private TextView errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent intent = new Intent(this, MainActivity.class);
        userEmail = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        errors = findViewById(R.id.errorView);

        /* form fields change listeners */
        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = userPassword.getText().toString();
                if (password.trim().length() > 5) {
                    loginButton.setEnabled(true);
                }
            }
        });

        /* button login click listener */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://theboom.xyz/engine/public/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                jsphapi = retrofit.create(JsonPlaceHolderAPI.class);
                String pass = "107989546880590330245";
                Call<User> call = jsphapi.sendUserData(userEmail.getText().toString(), pass);//userPassword.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()) {
                            errors.setVisibility(View.VISIBLE);
                            errors.setText("Code:" + response.code());
                            return;
                        }
                        User userProfile = response.body();
                        if (userProfile != null) {
                            /* add data for Home activity */
                            intent.putExtra("user", userProfile.toStringArray());
                            startActivity(intent);
                            System.out.println(userProfile.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        errors.setVisibility(View.VISIBLE);
                        errors.setText(t.getMessage());
                        System.out.println(t.getMessage());
                    }
                });
            }
        });
    }
}