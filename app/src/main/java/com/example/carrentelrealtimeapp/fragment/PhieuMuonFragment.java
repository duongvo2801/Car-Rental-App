package com.example.carrentelrealtimeapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.adapter.PhieuMuonAdapter;
import com.example.carrentelrealtimeapp.dao.KhachHangDAO;
import com.example.carrentelrealtimeapp.dao.PhieuMuonDAO;
import com.example.carrentelrealtimeapp.dao.XeDAO;
import com.example.carrentelrealtimeapp.model.KhachHang;
import com.example.carrentelrealtimeapp.model.PhieuMuon;
import com.example.carrentelrealtimeapp.model.Xe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PhieuMuonFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);

        recyclerView = view.findViewById(R.id.rvPhieuMuon);
        floatAdd = view.findViewById(R.id.floatAdd);

        // data
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
        // Interface

        // data
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();

        // adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_phieumuon, null);
        Spinner spnKhachHang = view.findViewById(R.id.spnKhachHang);
        Spinner spnXe = view.findViewById(R.id.spnXe);

        // add du lieu len dialog
        getDataThanhVien(spnKhachHang);
        getDataXe(spnXe);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // lay maTT
                HashMap<String, Object> hsKH = (HashMap<String, Object>) spnKhachHang.getSelectedItem();
                int maTV = (int) hsKH.get("maKH");

                // lay xe
                HashMap<String, Object> hsXe = (HashMap<String, Object>) spnXe.getSelectedItem();
                int maXe = (int) hsXe.get("maXe");
                int tienThue = (int) hsXe.get("giaThue");

                addPhieuMuon(maTV, maXe, tienThue);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void getDataThanhVien(Spinner spnKhachHang) {
        KhachHangDAO KhachHangDAO = new KhachHangDAO(getContext());
        ArrayList<KhachHang> list = KhachHangDAO.getDSKhachHang();

        //
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(KhachHang tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maKH", tv.getMaKH());
            hs.put("hoTen", tv.getHoTen());
            listHM.add(hs);
        }

        // ánh xạ data vào View
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoTen"},
                new int[]{android.R.id.text1});
        spnKhachHang.setAdapter(simpleAdapter);

    }

    private void getDataXe(Spinner spnXe) {
        XeDAO xeDAO = new XeDAO(getContext());
        ArrayList<Xe> list = xeDAO.getDSDauXe();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(Xe s : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maXe", s.getMaXe());
            hs.put("tenXe", s.getTenXe());
            hs.put("giaThue", s.getGiaThue());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenXe"},
                new int[]{android.R.id.text1});
        spnXe.setAdapter(simpleAdapter);

    }

    private void addPhieuMuon(int maKH, int maXe, int tienThue) {
        // lay maTT - phần login
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String maTT = sharedPreferences.getString("maTT", "");

        // lay ngay (Hien tai)
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String date = simpleDateFormat.format(currentTime);

        //
        PhieuMuon phieuMuon = new PhieuMuon(maKH, maTT, maXe, date, 0, tienThue);
        boolean check = phieuMuonDAO.addPhieuMuon(phieuMuon);
        if(check) {
            Toast.makeText(getContext(), "Them phieu muon thanh cong", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Them phieu muon that bai", Toast.LENGTH_SHORT).show();
        }
    }
}