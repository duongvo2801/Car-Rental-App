package com.example.carrentelrealtimeapp.model;

public class PhieuMuon {
    private int maPM;
    private int maKH;
    private String maTT;
    private int maXe;
    private String ngayMuon;
    private int trangThai;
    private int tienThue;

    // pm.maPM, pm.maKH, tv.hoten, pm.maTT, tt.hoten, pm.maXe, sc.tensach, pm.ngayMuon, pm.trangThai, pm.tienthue
    public PhieuMuon(int maPM, int maKH, String maTT, int maXe, String ngayMuon, int trangThai, int tienThue) {
        this.maPM = maPM;
        this.maKH = maKH;
        this.maTT = maTT;
        this.maXe = maXe;
        this.ngayMuon = ngayMuon;
        this.trangThai = trangThai;
        this.tienThue = tienThue;
    }

    public PhieuMuon(int maKH, String maTT, int maXe, String ngayMuon, int trangThai, int tienThue) {
        this.maKH = maKH;
        this.maTT = maTT;
        this.maXe = maXe;
        this.ngayMuon = ngayMuon;
        this.trangThai = trangThai;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
