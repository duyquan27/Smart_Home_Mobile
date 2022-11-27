package com.example.afinal.fragment_home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.login.SignInActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragmentSetting extends Fragment {
    DatabaseReference mHome;
    Switch aSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_setting, container, false);
        // Firebase
        mHome = FirebaseDatabase.getInstance().getReference();
        aSwitch = mview.findViewById(R.id.switch_noty_off);
        FrameLayout btn_account = mview.findViewById(R.id.btn_account);
        FrameLayout btn_logout = mview.findViewById(R.id.btn_logout);
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccountDialog(Gravity.CENTER);
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    mHome.child("SETTING").child("Notifications").setValue("ON");
                else
                    mHome.child("SETTING").child("Notifications").setValue("OFF");
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SignInActivity.class));
            }
        });
        return mview;
    }
    private void openAccountDialog(int gravity){
        final Dialog dialog = new Dialog(getContext());
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