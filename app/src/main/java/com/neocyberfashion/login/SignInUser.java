package com.neocyberfashion.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.neocyberfashion.R;
import com.neocyberfashion.networkConfig.retrofitAuth.RetrofitAuthClient;
import com.neocyberfashion.register.UserRegistration;
import java.util.regex.Pattern;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInUser extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private TextView forgetPassword;
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
        MaterialButton signInUserButton = findViewById(R.id.signInButton);
        progressbar = findViewById(R.id.progressBar);
        signUpUser = findViewById(R.id.signUp);

        signInUserButton.setOnClickListener(v -> {
            if(emailAddress.getText().toString().isEmpty())
            {
                emailAddress.setError("Email address can not be empty");
                emailAddress.requestFocus();
            }
            else if(!isValidEmailId(emailAddress.getText().toString()))
            {
                emailAddress.setError("Please enter valid email address");
                emailAddress.requestFocus();
            }
            else if(password.getText().toString().isEmpty())
            {
                password.setError("Password can not be empty");
                password.requestFocus();
            }
            else
                signInUser();
        });
    }

    private void signInUser() {

        UserRegistration userRegistration = new UserRegistration();

        userRegistration.setEmailId(emailAddress.getText().toString().trim());
        userRegistration.setPassword(password.getText().toString());

        progressbar.setVisibility(View.VISIBLE);

        RetrofitAuthClient.getRetrofitAuthClient().getAuthApis()
                .authenticateUser(userRegistration)
                .enqueue(new Callback<AuthenticationResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthenticationResponse> call, @NonNull Response<AuthenticationResponse> response) {
                        if(response.isSuccessful())
                        {
                            AuthenticationResponse authenticationResponse = response.body();

                            assert authenticationResponse != null;
                            Paper.book().write("jwt", authenticationResponse.getJwt());
                            progressbar.setVisibility(View.GONE);
                        }
                        else
                        {
                            progressbar.setVisibility(View.GONE);

                            if(response.code() == 401)
                                Toast.makeText(SignInUser.this, "Email or password not correct", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SignInUser.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthenticationResponse> call, @NonNull Throwable t) {
                        progressbar.setVisibility(View.GONE);
                        Log.e("SignInUser", t.getMessage());
                        Toast.makeText(SignInUser.this, "Failed to login, please try again", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SignInUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}