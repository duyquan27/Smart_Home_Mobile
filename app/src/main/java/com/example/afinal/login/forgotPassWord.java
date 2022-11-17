package com.example.afinal.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.afinal.R;

public class forgotPassWord extends AppCompatActivity {

    TextView btnSignIn;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.forgot_password);

        btnSignIn = (TextView) findViewById(R.id.btnSignIn);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentForgotPassWord myFragment = new fragmentForgotPassWord();
        fragmentTransaction.replace(R.id.fragment,myFragment, "FG_FRAGMENT");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgotPassWord.this,SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentForgotPassWord test = (fragmentForgotPassWord) getSupportFragmentManager().findFragmentByTag("FG_FRAGMENT");
                if (test != null && test.isVisible())
                {
                    Intent intent = new Intent(forgotPassWord.this,SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

    }
}
