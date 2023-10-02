package com.example.carrentelrealtimeapp.model;

public class Xe {
    private int maXe;
    private String tenXe;
    private int giaThue;
    private int maLoai;
    private int soLuongDaMuon;
    private String tenLoai;

    public Xe(int maXe, String tenXe, int giaThue, int maLoai) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Xe(int maXe, String tenXe, int soLuongDaMuon) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public Xe(int maXe, String tenXe, int giaThue, int maLoai, String tenLoai) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(int soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
