package com.example.carrentelrealtimeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;
import com.example.carrentelrealtimeapp.model.PhieuMuon;

import java.util.ArrayList;


public class PhieuMuonDAO {
    DbHelper dbHelper;

    public PhieuMuonDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON ORDER BY PHIEUMUON.maPM DESC", null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());

        }

        return list;
    }

    public boolean changeAction(int maPM) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThai", 1);
        long check = sqLiteDatabase.update("PHIEUMUON", contentValues, "maPM =?", new String[]{String.valueOf(maPM)});
        if(check == -1) {
            return false;
        }
        return true;
    }

    public boolean addPhieuMuon(PhieuMuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // maPM INTEGER PRIMARY KEY AUTOINCREMENT, maTV integer REFERENCES THANHVIEN (maTV), maTT TEXT REFERENCES THUTHU (maTT), maSach INTEGER REFERENCES SACH (maSach), ngay TEXT, traSach  INTEGER, tienThue INTEGER
        contentValues.put("maKH", phieuMuon.getMaKH());
        contentValues.put("maTT", phieuMuon.getMaTT());
        contentValues.put("maXe", phieuMuon.getMaXe());
        contentValues.put("ngayMuon", phieuMuon.getNgayMuon());
        contentValues.put("trangThai", phieuMuon.getTrangThai());
        contentValues.put("tienThue", phieuMuon.getTienThue());

        long check = sqLiteDatabase.insert("PHIEUMUON", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }


}
