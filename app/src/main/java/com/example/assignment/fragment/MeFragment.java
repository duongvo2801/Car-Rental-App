package com.example.assignment.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.assignment.R;
import com.example.assignment.me.ChangePasswordActivity;
import com.example.assignment.me.DoanhThuActivity;
import com.example.assignment.account.Login;

public class MeFragment extends Fragment {
    private LinearLayout linearChangePass, linearDoanhThu, linearLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        linearDoanhThu = view.findViewById(R.id.linearDoanhThu);
        linearChangePass = view.findViewById(R.id.linearChangePass);
        linearLogout = view.findViewById(R.id.linearLogout);

        linearDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DoanhThuActivity.class));
            }
        });

        linearChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}