package com.example.carrentelrealtimeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.model.Xe;
import com.example.carrentelrealtimeapp.model.Xe;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{
    private Context context;
    private ArrayList<Xe> list;

    public Top10Adapter(Context context, ArrayList<Xe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaXe.setText("Mã Xe:        " + String.valueOf(list.get(position).getMaXe()));
        holder.tvTenXe.setText("Tên Xe:       " + list.get(position).getTenXe());
        holder.tvSoLuongMuon.setText("Số lượng:   " + String.valueOf(list.get(position).getSoLuongDaMuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaXe, tvTenXe, tvSoLuongMuon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaXe = itemView.findViewById(R.id.tvMaXe);
            tvTenXe = itemView.findViewById(R.id.tvTenXe);
            tvSoLuongMuon = itemView.findViewById(R.id.tvSoLuongMuon);
        }
    }


}
