package com.example.carrentelrealtimeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.dao.PhieuMuonDAO;
import com.example.carrentelrealtimeapp.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // quản lý layout của item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater là 1 component giúp chuyển layout file(Xml) thành View(Java code) trong Android
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phieumuon, parent, false);

        return new ViewHolder(view);
    }

    // set du lieu len listview
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaPM.setText("Mã PM:             " + list.get(position).getMaPM());
        holder.tvMaKH.setText("Mã KH:              " + list.get(position).getMaKH());
        holder.tvMaTT.setText("Mã TT:               " + list.get(position).getMaTT());
        holder.tvMaXe.setText("Mã Xe:               " + list.get(position).getMaXe());
        holder.tvNgayMuon.setText("Ngày Mượn:     " + list.get(position).getNgayMuon());

        String trangThai = "";
        if(list.get(position).getTrangThai() == 1) {
            trangThai = "Đã trả xe";
            // ẩn btn đã trả
            holder.btnTraXe.setVisibility(View.GONE);
        } else {
            trangThai = "Chưa trả xe";
            holder.btnTraXe.setVisibility(View.VISIBLE);
        }
        holder.tvTrangThai.setText("Trạng thái:        " + trangThai);

        holder.tvTienThue.setText("Mã PM:             " + list.get(position).getTienThue());

        holder.btnTraXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean check = phieuMuonDAO.changeAction(list.get(holder.getAdapterPosition()).getMaPM());
                if(check) {
                    // xóa du lieu cu
                    list.clear();
                    list = phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Thay doi khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // quản lý các item
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPM, tvMaKH, tvMaTT, tvMaXe, tvNgayMuon, tvTrangThai, tvTienThue;
        Button btnTraXe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPM = itemView.findViewById(R.id.tvMaPM);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvMaTT = itemView.findViewById(R.id.tvMaTT);
            tvMaXe = itemView.findViewById(R.id.tvMaXe);
            tvNgayMuon = itemView.findViewById(R.id.tvNgayMuon);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            btnTraXe = itemView.findViewById(R.id.btnTraXe);
        }
    }

}
