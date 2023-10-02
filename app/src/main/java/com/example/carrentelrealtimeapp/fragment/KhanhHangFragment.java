package com.example.carrentelrealtimeapp.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.adapter.KhachHangAdapter;
import com.example.carrentelrealtimeapp.dao.KhachHangDAO;
import com.example.carrentelrealtimeapp.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhanhHangFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);

        recyclerView = view.findViewById(R.id.rvThanhVien);
        floatAdd = view.findViewById(R.id.floatAdd);

        khachHangDAO = new KhachHangDAO(getContext());
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }

    private void loadData() {
        list = khachHangDAO.getDSKhachHang();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        KhachHangAdapter adapter = new KhachHangAdapter(getContext(), list, khachHangDAO);
        recyclerView.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_khach_hang, null);
        builder.setView(view);

        EditText etHoTen = view.findViewById(R.id.etHoTen);
        EditText etNamSinh = view.findViewById(R.id.etNamSinh);

//        getDataThanhVien(spnThanhVien);
//        getDataSach(spnSach);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
                String hoTen = etHoTen.getText().toString();
                String namSinh = etNamSinh.getText().toString();

                boolean check = khachHangDAO.addKhachHang(hoTen, namSinh);
                if(check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    // load data
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}