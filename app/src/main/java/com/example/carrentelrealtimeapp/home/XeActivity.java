package com.example.carrentelrealtimeapp.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.adapter.XeAdapter;
import com.example.carrentelrealtimeapp.dao.LoaiXeDAO;
import com.example.carrentelrealtimeapp.dao.XeDAO;
import com.example.carrentelrealtimeapp.model.LoaiXe;
import com.example.carrentelrealtimeapp.model.Xe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class XeActivity extends AppCompatActivity {
    XeDAO xeDAO;
    ArrayList<Xe> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe);

        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);

        recyclerView = findViewById(R.id.rvXe);
        xeDAO = new XeDAO(XeActivity.this);
        loadData();


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void loadData() {
        list = xeDAO.getDSDauXe();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XeActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        XeAdapter adapter = new XeAdapter(XeActivity.this, list, getDSLoaiXe(), xeDAO);
        recyclerView.setAdapter(adapter);
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(XeActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_xe, null);
        builder.setView(view);

        EditText etTenXe = view.findViewById(R.id.etTenXe);
        EditText etTien = view.findViewById(R.id.etTien);
        Spinner spnLoaiXe = view.findViewById(R.id.spnLoaiXe);

        SimpleAdapter simpleAdapter =new SimpleAdapter(
                XeActivity.this,
                getDSLoaiXe(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiXe.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
                String tenXe = etTenXe.getText().toString();
                int tien = Integer.parseInt(etTien.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiXe.getSelectedItem();
                int maLoai = (int) hs.get("maLoai");

                boolean check = xeDAO.addXe(tenXe, tien, maLoai);
                if(check) {
                    Toast.makeText(XeActivity.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(XeActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
//
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiXe() {
        LoaiXeDAO loaiXeDAO = new LoaiXeDAO(XeActivity.this);
        ArrayList<LoaiXe> list = loaiXeDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for(LoaiXe ls : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", ls.getId());
            hs.put("tenLoai", ls.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}