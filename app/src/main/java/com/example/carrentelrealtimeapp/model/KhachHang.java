package com.example.carrentelrealtimeapp.model;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String namSinh;

    public KhachHang(int maKH, String hoTen, String namSinh) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
