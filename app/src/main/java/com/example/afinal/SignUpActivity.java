package com.example.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    private Button openEye;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private boolean checkEye = true;

    private String EMAIL_PATTERN = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";
    private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    private String PHONE_PATTERN = "^[0-9]{10}$";
    private String PASSWORD_PATTERN = "^[a-zA-z0-9]{6,20}$";

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
        openEye = (Button) findViewById(R.id.openEye);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        closePassword();
        openEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEye)
                {
                    closePassword();
                    checkEye = false;
                }
                else
                {
                    openPassword();
                    checkEye = true;
                }
            }
        });

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

        //reset error
        txtUsername.setError(null);
        txtEmail.setError(null);
        txtPhone.setError(null);
        txtPassword.setError(null);

        String name = txtUsername.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String phone = txtPhone.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        boolean cancel = true;

        if (name.matches("")){
            txtUsername.setError(getString(R.string.error_field_username_empty));
            cancel = false;
        }
        else if (!name.matches(USERNAME_PATTERN)){
            txtUsername.setError(getString(R.string.error_field_username_required));
            cancel = false;
        }
        else if (email.matches("")){
            txtEmail.setError(getString(R.string.error_field_email_empty));
            cancel = false;
        }
        else if (!email.matches(EMAIL_PATTERN)) {
            txtEmail.setError(getString(R.string.error_field_email_required));
            cancel = false;
        }
        else if (phone.matches("")){
            txtPhone.setError(getString(R.string.error_field_phone_empty));
            cancel = false;
        }
        else if (!phone.matches(PHONE_PATTERN)) {
            txtPhone.setError(getString(R.string.error_field_phone_required));
            cancel = false;
        }
        else if (password.matches("")){
            txtPassword.setError(getString(R.string.error_field_password_empty));
            cancel = false;
        }
        else if (!password.matches(PASSWORD_PATTERN)) {
            txtPassword.setError(getString(R.string.error_field_password_required));
            cancel = false;
        }

        if (cancel) {
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
                                                //
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

    private void closePassword()
    {
        openEye.setBackgroundResource(R.drawable.eye_open);
        txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        txtPassword.setSelection(txtPassword.getText().length());
    }

    private void openPassword()
    {
        openEye.setBackgroundResource(R.drawable.eye_close);
        txtPassword.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        txtPassword.setSelection(txtPassword.getText().length());
    }
}
