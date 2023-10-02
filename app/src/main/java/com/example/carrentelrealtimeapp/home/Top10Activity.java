package com.example.carrentelrealtimeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carrentelrealtimeapp.MainActivity;
import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.adapter.Top10Adapter;
import com.example.carrentelrealtimeapp.dao.Top10DAO;
import com.example.carrentelrealtimeapp.model.Xe;

import java.util.ArrayList;

public class Top10Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        RecyclerView recyclerView = findViewById(R.id.rvTop10);
        Top10DAO top10DAO = new Top10DAO(Top10Activity.this);
        ArrayList<Xe> list = top10DAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(this, list);
        recyclerView.setAdapter(adapter);

        // status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }
}