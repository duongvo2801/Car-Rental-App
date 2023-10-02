package com.example.carrentelrealtimeapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.home.Top10Activity;
import com.example.carrentelrealtimeapp.home.XeActivity;
import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.home.Top10Activity;
import com.example.carrentelrealtimeapp.home.XeActivity;

public class HomeFragment extends Fragment {
    private TextView tvTop10, tvDSXe;
    private LinearLayout llTop1, llTop2, llTop3, llDSXe1, llDSXe2, llDSXe3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // mapping
        tvTop10 = view.findViewById(R.id.tvTop10);
        tvDSXe = view.findViewById(R.id.tvDSXe);
        llTop1 = view.findViewById(R.id.llTop1);
        llTop2 = view.findViewById(R.id.llTop2);
        llTop3 = view.findViewById(R.id.llTop3);
        llDSXe1 = view.findViewById(R.id.llDSXe1);
        llDSXe2 = view.findViewById(R.id.llDSXe2);
        llDSXe3 = view.findViewById(R.id.llDSXe3);

        tvTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Top10Activity.class));
            }
        });

        llTop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Top10Activity.class));
            }
        });
        llTop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Top10Activity.class));
            }
        });
        llTop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Top10Activity.class));
            }
        });

        llDSXe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), XeActivity.class));
            }
        });
        llDSXe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), XeActivity.class));
            }
        });
        llDSXe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), XeActivity.class));
            }
        });

        tvDSXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),  XeActivity.class));
            }
        });

        return view;
    }
}