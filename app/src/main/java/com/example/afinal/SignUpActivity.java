package com.example.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText txtUsername,txtEmail,txtPhone,txtPassword;
    private ImageButton btnSignUp;
    private TextView btnSignIn;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private String EMAIL_PATTERN = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignUp = (ImageButton) findViewById(R.id.btnSignUp);
        btnSignIn = (TextView) findViewById(R.id.btnSignIn);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
        }
        else if (!email.matches(EMAIL_PATTERN)){
            txtEmail.setError("Noti");
        }
        else if (phone.isEmpty()){
            txtPhone.setError("Noti");
        }
        else if (password.isEmpty()){
            txtPassword.setError("Noti");
        }
        else {
            progressDialog.setMessage("Please Wait While Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                USER_INFOR USERINFOR = new USER_INFOR(name,email,phone,password);
                                FirebaseDatabase.getInstance().getReference("USER_INFO")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(USERINFOR).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
//                                                Toast.makeText(SignUpActivity.this,"Registered successfully!",Toast.LENGTH_LONG).show();
//                                                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
//                                                startActivity(intent);
                                                }
                                                else {
                                                    //Toast.makeText(SignUpActivity.this,"Failed to Registered! Try again",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                Toast.makeText(SignUpActivity.this,"Registered successfully!",Toast.LENGTH_LONG).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this,"Failed to Registered!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }


    }
}
