package com.example.afinal.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private ImageButton btnSignIn;
    private TextView btnForgotpw,btnSignUp;
    private EditText txtEmail, txtPassword;
    private Button openEye;
    private CheckBox checkboxRememberMe;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private USER_INFOR user_infor;

    private ProgressDialog progressDialog;

    private boolean checkEye = true;
    private boolean checkLogin = true;

    private String EMAIL_PATTERN = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";
    private String PHONE_PATTERN = "^[0-9]{10}$";

    private String userName, userEmail, userPhone, userPassword;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.log_in);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

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
        mDatabase = FirebaseDatabase.getInstance();

        user_infor = new USER_INFOR();
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

            if (email.matches(PHONE_PATTERN)) {
                mRef = mDatabase.getReference("USER/PHONE");
                mRef.child(email).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {

                            if (task.getResult().exists()) {

                                DataSnapshot dataSnapshot = task.getResult();
                                userEmail = String.valueOf(dataSnapshot.child("userEmail").getValue());
                                userName = String.valueOf(dataSnapshot.child("userName").getValue());
                                userPassword = String.valueOf(dataSnapshot.child("userPassword").getValue());
                                userPhone = String.valueOf(dataSnapshot.child("userPhone").getValue());

                                if (password.equals(userPassword)) {
                                    progressDialog.dismiss();
                                    sendUserToMainActivity();
                                    Toast.makeText(SignInActivity.this,userName,Toast.LENGTH_LONG).show();
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignInActivity.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                                }

                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(SignInActivity.this,"Incorrect Phone Number",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            }

            else if (!email.matches(PHONE_PATTERN)){
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            getUserData(email,password);
                            sendUserToMainActivity();
                            Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(SignInActivity.this,"Email or Password is Incorrect",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }

    private void sendUserToMainActivity() {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
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

    private String FindUserID(String email)
    {
        String replaceStr = email.replace(".","1");
        return replaceStr;
    }

    private void getUserData(String email, String password) {
        mRef = mDatabase.getReference("USER/UID");
        mRef.child(FindUserID(email)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        userEmail = String.valueOf(dataSnapshot.child("userEmail").getValue());
                        userName = String.valueOf(dataSnapshot.child("userName").getValue());
                        userPassword = String.valueOf(dataSnapshot.child("userPassword").getValue());
                        userPhone = String.valueOf(dataSnapshot.child("userPhone").getValue());
                        Toast.makeText(SignInActivity.this, userName, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
