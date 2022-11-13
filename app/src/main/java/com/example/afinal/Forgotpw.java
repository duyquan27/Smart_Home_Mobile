package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Forgotpw extends AppCompatActivity {

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

        fragment_forgotpw myFragment = new fragment_forgotpw();
        fragmentTransaction.replace(R.id.fragment,myFragment, "FG_FRAGMENT");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forgotpw.this,SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_forgotpw test = (fragment_forgotpw) getSupportFragmentManager().findFragmentByTag("FG_FRAGMENT");
                if (test != null && test.isVisible())
                {
                    Intent intent = new Intent(Forgotpw.this,SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

    }
}
