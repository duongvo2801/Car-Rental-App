package com.example.carrentelrealtimeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DANGKYMUONXE";
    private static final int DB_VERSION = 1;


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbThuThu = "CREATE TABLE THUTHU (maTT TEXT PRIMARY KEY, hoTen TEXT, matKhau TEXT, loaiTaiKhoan TEXT);";
        db.execSQL(dbThuThu);

        String dbKhachHang = "CREATE TABLE KHACHHANG (maKH integer PRIMARY KEY AUTOINCREMENT, hoTen TEXT, namSinh TEXT);";
        db.execSQL(dbKhachHang);

        String dbXe = "CREATE TABLE XE (maXe INTEGER PRIMARY KEY AUTOINCREMENT, tenXe TEXT, giaThue INTEGER, maLoai  INTEGER REFERENCES LOAIXE (maLoai) );";
        db.execSQL(dbXe);

        String dbLoaiXe = "CREATE TABLE LOAIXE (maLoai  INTEGER PRIMARY KEY AUTOINCREMENT, tenLoai TEXT);";
        db.execSQL(dbLoaiXe);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON (maPM INTEGER PRIMARY KEY AUTOINCREMENT, maKH integer REFERENCES KHACHHANG (maKH), maTT TEXT REFERENCES THUTHU (maTT), maXe INTEGER REFERENCES XE (maXe), ngayMuon TEXT, trangThai  INTEGER, tienThue INTEGER );";
        db.execSQL(dbPhieuMuon);

        // data
        db.execSQL("INSERT INTO LOAIXE VALUES (1, '50cc'), (2, '100cc'), (3, '125cc'), (4, '150cc'), (5, 'Trên 170cc (PKL)')");
        db.execSQL("INSERT INTO XE VALUES (1, 'Winner', 2300, 1), (2, 'Exciter', 6530, 2), (3, 'SH', 7300, 3), (4, 'Cub', 15300, 1), (5, 'Vespa', 7600, 6), (6, 'Vision', 17400, 4), (7, 'Dream', 4300, 5), (8, 'Jupiter', 1900, 5), (9, 'Benelli 302S', 19500, 5)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu001', 'Trần Tiến', 'abc001', 'Admin'), ('thuthu002', 'Nhật Khánh', 'abc002', 'Thu Thu'), ('thuthu003', 'Văn Đoàn', 'abc003', 'Thu Thu')");
        db.execSQL("INSERT INTO KHACHHANG VALUES (1, 'Văn Hiếu','1997'), (2, 'Hoài Nam','2007'), (3, 'Quốc Khánh','2002'), (4, 'Nguyễn Như','2000'), (5, 'Bùi Đạt','2002')");
        // 1: đã trả - 0: chưa trả (tháng :07 - 10)
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/09/2023', 1, 2500), (2,1,'thuthu01', 3, '22/09/2023', 0, 2000), (3,5,'thuthu02', 7, '01/10/2023', 0, 7400), (4,3,'thuthu02', 6, '04/10/2023', 1, 7700), (5,4,'thuthu01', 4, '05/10/2023', 0,4500), (6, 1,'thuthu03', 5, '10/10/2023', 1, 1900), (7, 3,'thuthu02', 8, '20/09/2023', 0, 8700), (8, 3,'thuthu01', 4, '29/09/2023', 0, 14300)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // nếu version thay đổi (old - new)
        if(i != i1) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS XE");
            db.execSQL("DROP TABLE IF EXISTS LOAIXE");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");

            onCreate(db);
        }
    }
}
