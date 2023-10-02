package com.example.carrentelrealtimeapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.dao.XeDAO;
import com.example.carrentelrealtimeapp.model.Xe;

import java.util.ArrayList;
import java.util.HashMap;

public class XeAdapter extends RecyclerView.Adapter<XeAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Xe> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private XeDAO xeDAO;

    public XeAdapter(Context context, ArrayList<Xe> list, ArrayList<HashMap<String, Object>> listHM, XeDAO xeDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.xeDAO = xeDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_xe, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaXe.setText("Mã xe:        " + list.get(position).getMaXe());
        holder.tvTenXe.setText("Tên xe:       " + list.get(position).getTenXe());
        holder.tvGiaThue.setText("Giá thuê:    " + list.get(position).getGiaThue());
        holder.tvMaLoai.setText("Mã loại:      " + list.get(position).getMaLoai());
        holder.tvTenLoai.setText("Tên loại:     " + list.get(position).getTenLoai());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = xeDAO.deleteXe(list.get(holder.getAdapterPosition()).getMaXe());
                switch (check) {
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Xóa thể xóa vì xe đã tồn tại", Toast.LENGTH_SHORT).show();
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
        TextView tvMaXe, tvTenXe, tvGiaThue, tvMaLoai, tvTenLoai;
        ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaXe = itemView.findViewById(R.id.tvMaXe);
            tvTenXe = itemView.findViewById(R.id.tvTenXe);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    private void showDialog(Xe xe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_edit_xe, null );
        builder.setView(view);

        EditText etTenXe = view.findViewById(R.id.etTenXe);
        EditText etTien = view.findViewById(R.id.etTien);
        TextView tvMaXe = view.findViewById(R.id.tvMaXe);
        Spinner spnLoaiXe = view.findViewById(R.id.spnLoaiXe);

        tvMaXe.setText("Mã sách: " + xe.getMaXe());
        etTenXe.setText(xe.getTenXe());
        etTien.setText(String.valueOf(xe.getGiaThue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiXe.setAdapter(simpleAdapter);
        // tim vi tri cua spnLoaiXe
        int index = 0;
        int position = -1;
        for (HashMap<String, Object> item : listHM) {
            if((int)item.get("maLoai") == xe.getMaLoai()) {
                position = index;
            }
            index++;
        }
        spnLoaiXe.setSelection(position);

        builder.setNegativeButton("Cật nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenXe = etTenXe.getText().toString();
                int tien = Integer.parseInt(etTien.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiXe.getSelectedItem();
                int maLoai = (int) hs.get("maLoai");

                boolean check = xeDAO.updateXe(xe.getMaXe(), tenXe, tien, maLoai);
                if(check) {
                    Toast.makeText(context, "Cật nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(context, "Cật nhật thất bại", Toast.LENGTH_SHORT).show();
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

    public void loadData() {
        list.clear();
        list = xeDAO.getDSDauXe();
        notifyDataSetChanged();
    }
}
