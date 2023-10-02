package com.example.carrentelrealtimeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.dao.KhachHangDAO;
import com.example.carrentelrealtimeapp.model.KhachHang;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private Context context;
    private ArrayList<KhachHang> list;
    private KhachHangDAO khachHangDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list, KhachHangDAO khachHangDAO) {
        this.context = context;
        this.list = list;
        this.khachHangDAO = khachHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_khach_hang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaTV.setText("Mã TV:        " + list.get(position).getMaKH());
        holder.tvHoTen.setText("Họ tên:        " + list.get(position).getHoTen());
        holder.tvNamSinh.setText("Năm sinh:   " + list.get(position).getNamSinh());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogUpdateTT(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = khachHangDAO.deleteThongTinKH(list.get(holder.getAdapterPosition()).getMaKH());
                switch (check) {
                    case 1:
                        Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thành viên thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa vì thành viên đã tồn tại", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV, tvHoTen, tvNamSinh;
        ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvHoTen = itemView.findViewById(R.id.tvHoten);
            tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public void showDialogUpdateTT(KhachHang khachHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_update_khach_hang, null);
        builder.setView(view);

        TextView tvMaKH = view.findViewById(R.id.tvMaKH);
        TextView etHoTen = view.findViewById(R.id.etHoTen);
        TextView etNamSinh = view.findViewById(R.id.etNamSinh);

        tvMaKH.setText("Mã TV: " + khachHang.getMaKH());
        etHoTen.setText(khachHang.getHoTen());
        etNamSinh.setText(khachHang.getNamSinh());

        builder.setNegativeButton("Cật nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoTen = etHoTen.getText().toString();
                String namSinh = etNamSinh.getText().toString();
                int id = khachHang.getMaKH();

                boolean check = khachHangDAO.updateThongTinKH(id, hoTen, namSinh);
                if(check) {
                    Toast.makeText(context, "Cật nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    // load data
                    loadData();
                } else {
                    Toast.makeText(context, "Cật nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
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

    private void loadData() {
        list.clear();
        list = khachHangDAO.getDSKhachHang();
        notifyDataSetChanged();
    }
}
