package com.neocyberfashion.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.neocyberfashion.R;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        //Change status bar color
//        getWindow().setStatusBarColor(Color.parseColor("#4E0F0E"));
//        getWindow().setNavigationBarColor(Color.parseColor("#000000"));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        TextView textView = findViewById(R.id.start_shopping);

        textView.setOnClickListener(v -> {
            state = 1;
            Intent intent = new Intent(MainActivity.this, WelcomeScreen1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        new Handler().postDelayed(() -> {
            if(state == 0)
            {
                Intent mainIntent = new Intent(MainActivity.this, WelcomeScreen1.class);
                startActivity(mainIntent);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        }, 4000);

    }
}