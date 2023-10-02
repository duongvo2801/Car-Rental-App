package com.example.carrentelrealtimeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;
import com.example.carrentelrealtimeapp.model.Xe;

import java.util.ArrayList;

public class XeDAO {
    DbHelper dbHelper;

    public XeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Lay all dau xe
    public ArrayList<Xe> getDSDauXe() {
        ArrayList<Xe> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        // đọc và lưu tt data theo hàng
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT x.maXe, x.tenXe, x.giaThue, x.maLoai, lx.tenLoai FROM XE x , LOAIXE lx WHERE x.maLoai = lx.maLoai", null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Xe(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            } while (cursor.moveToNext());

        }

        return list;
    }

    public boolean addXe(String tenXe, int giaThue, int maLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //
        contentValues.put("tenXe", tenXe);
        contentValues.put("giaThue", giaThue);
        contentValues.put("maLoai", maLoai);

        long check = sqLiteDatabase.insert("XE", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }

    public boolean updateXe(int maXe, String tenXe, int giaThue, int maLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenXe", tenXe);
        contentValues.put("giaThue", giaThue);
        contentValues.put("maLoai", maLoai);

        long check = sqLiteDatabase.update("XE", contentValues, "maXe =?", new String[]{String.valueOf(maLoai)});
        if(check == -1) {
            return false;
        }
        return true;
    }

    // xoa loai xe: 1: xoa thanh cong - 0: xoa that bai - -1: co xe dang dung
    public int deleteXe(int maXe) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE maXe =?", new String[]{String.valueOf(maXe)});
        if(cursor.getCount() != 0) {
            return -1;
        }

        long check = sqLiteDatabase.delete("XE", "maXe =?", new String[]{String.valueOf(maXe)});
        if(check == -1) {
            return 0;
        }
        return 1;
    }
}
