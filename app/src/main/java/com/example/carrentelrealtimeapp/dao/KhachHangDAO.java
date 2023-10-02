package com.example.carrentelrealtimeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;
import com.example.carrentelrealtimeapp.model.KhachHang;

import java.util.ArrayList;

public class KhachHangDAO {
    DbHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Lay all thanh vien
    public ArrayList<KhachHang> getDSKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());

        }

        return list;
    }

    public boolean addKhachHang(String hoTen, String namSinh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //
        contentValues.put("hoTen", hoTen);
        contentValues.put("namSinh", namSinh);

        long check = sqLiteDatabase.insert("KHACHHANG", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }

    // true: xoa thanh cong - false: xoa that bai
    public boolean updateThongTinKH(int maKH, String hoTen, String namSinh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", hoTen);
        contentValues.put("namSinh", namSinh);
        long check = sqLiteDatabase.update("KHACHHANG", contentValues, "maKH =?", new String[]{String.valueOf(maKH)});
        if(check == -1) {
            return false;
        }
        return true;
    }

    //(tự quy định) 1: xoa thanh cong - 0: xoa that bai - -1: thanh vien dang co phieu muon
    public int deleteThongTinKH(int maKH) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE maKH =?", new String[]{String.valueOf(maKH)});
        if(cursor.getCount() != 0) {
            return -1;
        }

        long check = sqLiteDatabase.delete("KHACHHANG", "maKH =?", new String[]{String.valueOf(maKH)});
        if(check == -1) {
            return 0;
        }
        return 1;
    }
}
