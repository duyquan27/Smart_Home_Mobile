package com.example.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private ImageButton btnSignIn;
    private TextView btnForgotpw,btnSignUp;
    private EditText txtEmail, txtPassword;
    private Button openEye;
    private CheckBox checkboxRememberMe;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private ProgressDialog progressDialog;

    private boolean checkEye = true;

    private String EMAIL_PATTERN = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.log_in);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!!!!!!!!");

        btnSignIn = (ImageButton) findViewById(R.id.btnLogin);
        btnForgotpw = (TextView) findViewById(R.id.btnForgotpw);
        btnSignUp = (TextView) findViewById(R.id.btnSignUp);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        openEye = (Button) findViewById(R.id.openEye);
        checkboxRememberMe = (CheckBox) findViewById(R.id.remmberCheck);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        if (!new PrefManager(this).isUserLogedOut()) {
//            sendUsertoNewActivity();
//        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

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
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnForgotpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, forgotPassWord.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void Login() {

        //reset error
        txtEmail.setError(null);
        txtPassword.setError(null);

        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        boolean cancel = true;

        //check valid email
        if (email.matches("")) {
            txtEmail.setError(getString(R.string.error_field_email_empty));
            cancel = false;
        }
        else if (!email.matches(EMAIL_PATTERN)) {
            txtEmail.setError(getString(R.string.error_field_email_required));
            cancel = false;
        }

        //check valid password
        if (password.isEmpty()) {
            txtPassword.setError(getString(R.string.error_field_password_empty));
            cancel = false;
        }

        //check email and password with database
        if (cancel) {
            progressDialog.setMessage("Please Wait While Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userID = mUser.getUid();
                                progressDialog.dismiss();
                                rememberMe(email,password);
                                sendUsertoNewActivity(userID);
                                Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(SignInActivity.this,"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void sendUsertoNewActivity(String userID) {
        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("userID",userID);
        intent.putExtra("USER_INFO", myBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email,password);
    }

    private void rememberMe(String email, String password) {
        if (checkboxRememberMe.isChecked()) {
            saveLoginDetails(email,password);
        }
    }
}
