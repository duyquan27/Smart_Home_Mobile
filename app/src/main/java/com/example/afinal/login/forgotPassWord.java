package com.example.afinal.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class forgotPassWord extends AppCompatActivity {

    private static final String TAG = forgotPassWord.class.getName();

    private TextView btnSignIn;
    private ImageButton btnBack, btnSubmit;
    private FrameLayout yourAddress, yourOTP, yourNewPassword;
    private EditText txtAddress, txtYourOTP, txtNewPassword, txtConfirmNewPassword;
    private Button openEyeNewPass, openEyeConfirmNewPass;
    private String recevieOTP;
    private String receviePhone;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.forgot_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();
        
        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgotPassWord.this,SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yourAddress.getVisibility() == View.VISIBLE) {

                    String phoneNumber = txtAddress.getText().toString().trim();
                    if(phoneNumber.matches(""))
                    {
                        txtAddress.setError(getString(R.string.error_field_phone_empty));
                    }
                    else {
                        onClickVerifyPhoneNumber(phoneNumber);
                    }
                }

                if (yourOTP.getVisibility() == View.VISIBLE) {
                    String code = txtYourOTP.getText().toString().trim();
                    onClickSendOTP(code);
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (yourAddress.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(forgotPassWord.this,SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                if (yourOTP.getVisibility() == View.VISIBLE) {
                    yourOTP.setVisibility(View.GONE);
                    yourAddress.setVisibility(View.VISIBLE);
                }

                if (yourNewPassword.getVisibility() == View.VISIBLE) {
                    yourOTP.setVisibility(View.VISIBLE);
                    yourAddress.setVisibility(View.GONE);
                }

            }
        });

    }
    private void Init()
    {
        btnSignIn = (TextView) findViewById(R.id.btnSignIn);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSubmit = (ImageButton) findViewById(R.id.btnSubmit);
        yourAddress = (FrameLayout) findViewById(R.id.yourAddress);
        yourOTP = (FrameLayout) findViewById(R.id.yourOTP);
        yourNewPassword = (FrameLayout) findViewById(R.id.yourNewPassword);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtYourOTP = (EditText) findViewById(R.id.txtYourOTP);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtConfirmNewPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        openEyeNewPass = (Button) findViewById(R.id.openEyeNewPass);
        openEyeConfirmNewPass = (Button) findViewById(R.id.openEyeConfirmNewPass);

        yourAddress.setVisibility(View.VISIBLE);
        yourOTP.setVisibility(View.GONE);
        yourNewPassword.setVisibility(View.GONE);
    }

    private void onClickVerifyPhoneNumber(String phone)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredentialENTERPHONE(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(forgotPassWord.this, "onVerificationFailed",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationID, forceResendingToken);

                                goToEnterOTP(phone,verifycationID);

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredentialENTERPHONE(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(forgotPassWord.this, "The verification code entered was invalid",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void goToEnterOTP(String phone, String verificationID)
    {
        recevieOTP = verificationID;
        receviePhone = phone;
    }

    private void onClickSendOTP(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(recevieOTP, code);
        signInWithPhoneAuthCredentialENTEROTP(credential);
    }

    private void signInWithPhoneAuthCredentialENTEROTP(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(forgotPassWord.this, "The verification code entered was invalid",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
