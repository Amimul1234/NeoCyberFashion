package com.neocyberfashion.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.neocyberfashion.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change status bar color
        getWindow().setStatusBarColor(Color.parseColor("#4E0F0E"));
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));

        TextView textView = findViewById(R.id.start_shopping);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WelcomeScreen1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(MainActivity.this, WelcomeScreen1.class);
            startActivity(mainIntent);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
        }, 3000);

    }
}