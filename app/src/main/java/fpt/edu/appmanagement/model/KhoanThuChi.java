package fpt.edu.appmanagement.model;

public class KhoanThuChi {
    private int makhoan;
    private int tien;
    private int maloai;
    private String tenloai;

    public int getMakhoan() {
        return makhoan;
    }

    public void setMakhoan(int makhoan) {
        this.makhoan = makhoan;
    }

    public KhoanThuChi(int makhoan, int tien, int maloai, String tenloai) {
        this.makhoan = makhoan;
        this.tien = tien;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public KhoanThuChi(int tien, int maloai) {
        this.tien = tien;
        this.maloai = maloai;
    }

    public int getTien() {
        return tien;
    }
    public void getGiaTien(){

    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }


}
