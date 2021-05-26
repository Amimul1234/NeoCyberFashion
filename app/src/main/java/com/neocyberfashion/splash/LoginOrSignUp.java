package com.neocyberfashion.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.neocyberfashion.R;
import com.neocyberfashion.login.SignIn;
import com.neocyberfashion.register.RegisterNewUser;

public class LoginOrSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign_up);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button signInButton = findViewById(R.id.signInButton);
        TextView signUpText = findViewById(R.id.signUp);

        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginOrSignUp.this, SignIn.class);
            startActivity(intent);
        });

        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginOrSignUp.this, RegisterNewUser.class);
            startActivity(intent);
        });
    }
}