package com.example.afinal;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
    int eye = 0;
    public void hide(View view) {
        if (eye == 0)
        {
            findViewById(R.id.eye).setBackground(getResources().getDrawable(R.drawable.eye_close));
            eye = 1;
        }
        else
        {
            findViewById(R.id.eye).setBackground(getResources().getDrawable(R.drawable.eye_open));
            eye = 0;
        }
    }
}