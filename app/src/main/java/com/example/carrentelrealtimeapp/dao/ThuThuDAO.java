package com.example.carrentelrealtimeapp.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrentelrealtimeapp.database.DbHelper;


public class ThuThuDAO {
    static DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }

    // Login
    public boolean checkLogin(String user, String pass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        // maTT, hoTen, matKhau, loaiTaiKhoan
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE maTT =? AND matKhau =?", new String[]{user, pass});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            // ghi dữ liệu
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("maTT", cursor.getString(0));
            editor.putString("loaiTaiKhoan", cursor.getString(3));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    // Register
    public static boolean register(String user, String pass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", user);
        contentValues.put("matKhau", pass);
        long check = sqLiteDatabase.insert("THUTHU", null, contentValues);
        if(check == -1) {
            return false;
        }
        return true;
    }
    public static boolean checkUser(String user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE maTT =?", new String[]{user});
        if(cursor.getCount() > 0) {
            return true;
        }
        return false;
    }


    public boolean updatePass(String username, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE maTT =? AND matKhau =?", new String[]{username, oldPass});
        if(cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matKhau", newPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues, "maTT =?", new String[]{username});
            if(check == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

}
