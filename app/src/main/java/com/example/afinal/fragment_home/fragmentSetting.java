package com.example.afinal.fragment_home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.example.afinal.login.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragmentSetting extends Fragment {

    private MainActivity mMainActivity;
    private TextView tvUserName, tvPhoneNumber;
    private Button btnEditProfile;
    private RelativeLayout btnLogout;
    private String _userName, PATH, userID,_userEmail,_userPassword,_userPhone;
    private DatabaseReference mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_setting, container, false);

        btnEditProfile = mview.findViewById(R.id.btnEditProfile);
        btnLogout = mview.findViewById(R.id.btnLogout);
        mMainActivity = (MainActivity) getActivity();

        tvUserName = mview.findViewById(R.id.tvUserName);
        tvPhoneNumber = mview.findViewById(R.id.tvPhoneNumber);

//        PATH = mMainActivity.getPath();
//        userID = mMainActivity.getUserID();
//        readData(PATH,userID);

        tvUserName.setText("Hoang Duy");

        //String _edtPhone = "+84 " + _userPhone.substring(1);
        tvPhoneNumber.setText("+84947755275");

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccountDialog(Gravity.CENTER);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SignInActivity.class));
            }
        });
        return mview;
    }

    private void readData(String _path, String userID)
    {
        if (_path.equals("PHONE"))
        {
            mData = FirebaseDatabase.getInstance().getReference("USER/PHONE");
            getData(userID);
        }
        else if (_path.equals("EMAIL"))
        {
            mData = FirebaseDatabase.getInstance().getReference("USER/EMAIL");
            getData(userID);
        }
    }

    private void getData(String userID)
    {
        mData.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful())
                {
                    if (task.getResult().exists())
                    {
                        DataSnapshot snapshot = task.getResult();
                        _userName = String.valueOf(snapshot.child("userName"));
                        _userEmail = String.valueOf(snapshot.child("userEmail"));
                        _userPhone = String.valueOf(snapshot.child("userPhone"));
                        _userPassword = String.valueOf(snapshot.child("userPassword"));
                    }
                    else
                    {
                        _userName = "user_name";
                        _userEmail = "user_email";
                        _userPhone = "user_phone";
                        _userPassword = "user_password";
                    }
                }
            }
        });
    }

    private void openAccountDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if (Gravity.CENTER == gravity)
        {
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }
        EditText edtUsername = dialog.findViewById(R.id.ed_username);
        EditText edtPassword = dialog.findViewById(R.id.ed_password);
        EditText edtEmail = dialog.findViewById(R.id.ed_email);
        EditText edtPhone = dialog.findViewById(R.id.ed_phonenum);
        Button btn_clear = dialog.findViewById(R.id.btn_clear);
        Button btn_apply = dialog.findViewById(R.id.btn_apply);

        edtUsername.setText(_userName);
        edtEmail.setText(_userEmail);
        edtPhone.setText(_userPhone);
        edtPassword.setText(_userPassword);

        btn_apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String change = "changed ";
                int check = 0;
                if (!(edtUsername.getText().toString().equals("")))
                {
                    change += " username";
                    check = 1;
                }
                if (!(edtPassword.getText().toString().equals("")))
                {
                    change += " password";
                    check = 1;
                }
                if (!(edtEmail.getText().toString().equals("")))
                {
                    change += " email";
                    check = 1;
                }
                if (!(edtPhone.getText().toString().equals("")))
                {
                    change += " phone number";
                    check = 1;
                }
                if (check == 1)
                {
                    Toast.makeText(getContext(),change,Toast.LENGTH_SHORT).show();
                }
                else if (check == 0)
                {
                    Toast.makeText(getContext(),"nothing was changed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}