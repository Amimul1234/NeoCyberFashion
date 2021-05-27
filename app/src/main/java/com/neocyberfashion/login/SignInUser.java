package com.neocyberfashion.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.neocyberfashion.R;

public class SignInUser extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private TextView forgetPassword;
    private MaterialButton signInUserButton;
    private ProgressBar progressbar;
    private TextView signUpUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_user);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailAddress = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        forgetPassword = findViewById(R.id.forget_password);
        signInUserButton = findViewById(R.id.signInButton);
        progressbar = findViewById(R.id.progressBar);
        signUpUser = findViewById(R.id.signUp);

        signInUserButton.setOnClickListener(v -> {

        });


    }
}