package com.example.carrentelrealtimeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;
import com.example.carrentelrealtimeapp.model.LoaiXe;

import java.util.ArrayList;

public class LoaiXeDAO {
    DbHelper dbHelper;

    public LoaiXeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Lay all loai sach
    public ArrayList<LoaiXe> getDSLoaiSach() {
        ArrayList<LoaiXe> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIXE", null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiXe(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());

        }

        return list;
    }

    // them loai xe dao
    public boolean addLoaiXe(String tenLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.insert("LOAIXE", null, contentValues);
        if(check == -1) {
            return false;
        }
        return true;
    }

    // xoa loai sach: 1: xoa thanh cong - 0: xoa that bai - -1: co sach dang dung
    public int deleteLoaiXe(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM XE WHERE maLoai =?", new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0) {
            return -1;
        }

        long check = sqLiteDatabase.delete("LOAIXE", "maLoai =?", new String[]{String.valueOf(id)});
        if(check == -1) {
            return 0;
        }
        return 1;
    }

}
