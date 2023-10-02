package com.example.carrentelrealtimeapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;
import com.example.carrentelrealtimeapp.model.Xe;

import java.util.ArrayList;

public class Top10DAO {
    DbHelper dbHelper;

    public Top10DAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Lay all dau xe
    public ArrayList<Xe> getTop10() {
        ArrayList<Xe> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.maXe, x.tenXe, COUNT(pm.maXe) FROM PHIEUMUON pm, Xe x WHERE pm.maXe = x.maXe GROUP by pm.maXe, x.tenXe ORDER by COUNT(pm.maXe) DESC LIMIT 10", null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Xe(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());

        }

        return list;
    }

    public int getDoanhThu(String startDay, String endDay) { // 2022/09/30
        startDay = startDay.replace("/", "");
        endDay = endDay.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienThue) FROM PHIEUMUON WHERE substr(ngayMuon,7)||substr(ngayMuon,4,2)||substr(ngayMuon,1,2) between ? and ?", new String[]{startDay, endDay});
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}

