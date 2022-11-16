package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText txtUsername,txtEmail,txtPhone,txtPassword;
    private ImageButton btnSignUp;
    private TextView btnSignIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sign_up);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignUp = (ImageButton) findViewById(R.id.btnSignUp);
        btnSignIn = (TextView) findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

    }

    private void registerNewUser()
    {
        String name = txtUsername.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String phone = txtPhone.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (name.isEmpty()){
            txtUsername.setError("Full name is required");
            txtUsername.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Noti");
            txtEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            txtPhone.setError("Noti");
            txtPhone.requestFocus();
            return;
        }

        if (password.isEmpty()){
            txtPassword.setError("Noti");
            txtPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            USER_INFOR USERINFOR = new USER_INFOR(name,email,phone,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(USERINFOR).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this,"Registered successfully!",Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                                                startActivity(intent);
                                            }
                                            else {
                                                Toast.makeText(SignUpActivity.this,"Failed to Registered! Try again",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Failed to Registered!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
