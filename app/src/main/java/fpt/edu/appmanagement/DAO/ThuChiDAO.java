package fpt.edu.appmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.edu.appmanagement.database.KhoanThuChiDB;
import fpt.edu.appmanagement.model.KhoanThuChi;
import fpt.edu.appmanagement.model.Loai;

public class ThuChiDAO {
    KhoanThuChiDB khoanThuChiDB;

    public ThuChiDAO(Context context) {
        khoanThuChiDB = new KhoanThuChiDB(context);
    }

    // lấy dánh sách loại thu : chi
    public ArrayList<Loai> getDSLoaiThuChi(String loai){
        ArrayList<Loai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM loai", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                String trangthai = cursor.getString(2);
                if(trangthai.equals(loai)){
                    list.add(new Loai(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                }

            }while (cursor.moveToNext());
        }

        return list;
    }

    // thêm loại thu : chi
    public boolean themMoiLoaiThuChi(Loai loai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", loai.getTenloai());
        contentValues.put("trangthai", loai.getTrangthai());
        long check = sqLiteDatabase.insert("loai", null, contentValues);
        return check != -1;
    }

    // cập nhật loại thu/ chi
    public boolean capNhatLoaiThuChi(Loai loai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", loai.getMaloai());
        contentValues.put("tenloai", loai.getTenloai());
        contentValues.put("trangthai", loai.getTrangthai());
        long check = sqLiteDatabase.update("loai", contentValues, "maloai =?", new String[]{String.valueOf(loai.getMaloai())});
        return check != -1;
    }

    // xoá loại thu : chi
    public boolean xoaLoaiThuChi(int maLoai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        long check = sqLiteDatabase.delete("loai", "maloai =?",  new String[]{String.valueOf(maLoai)});
        return check != -1;
    }

    // lấy danh sách khoản
    public ArrayList<KhoanThuChi> getDSKhoanThuChi(String loai){
        ArrayList<KhoanThuChi> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
        String sql = "select k.makhoan, k.tien, k.maloai, l.tenloai from khoanthuchi k, loai l where k.maloai = l.maloai and l.trangthai=  ?";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{loai});

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new KhoanThuChi(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    // thêm mới khoản
    public boolean themMoiKhoanThuChi(KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tien", khoanThuChi.getTien());
        contentValues.put("maloai", khoanThuChi.getMaloai());
        long check = sqLiteDatabase.insert("khoanthuchi", null, contentValues);
        return check !=  -1;
    }

    public boolean capNhatKhoanThuChi(KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tien", khoanThuChi.getTien());
        contentValues.put("maloai", khoanThuChi.getMaloai());

        long check = sqLiteDatabase.update("khoanthuchi", contentValues, "makhoan = ?", new String[]{String.valueOf(khoanThuChi.getMakhoan())});
        return check != -1;

    }

    public boolean xoaKhoanThuChi(KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        long check = sqLiteDatabase.delete("khoanthuchi", "makhoan = ?", new String[]{String.valueOf(khoanThuChi.getMakhoan())});
        return check != -1;
    }


    public float[] getThongTinThuChi() {
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
        int thu = 0, chi = 0;
        //select sum(tien)
        //from giaodich
        //where maloai in (select maloai from phanloai where thangthai = 'thu')
        Cursor cursorThu = sqLiteDatabase.rawQuery("select sum(tien) from khoanthuchi where maloai in (select maloai from loai where trangthai = 'thu') ", null);
        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }

        float[] ketQua = new float[]{thu};
        return ketQua;
    }




}
