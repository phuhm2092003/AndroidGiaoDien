package fpt.edu.appmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KhoanThuChiDB extends SQLiteOpenHelper {
    public KhoanThuChiDB(Context context) {
        super(context, "THUCHIDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qLoai = "CREATE TABLE loai(maloai integer primary key autoincrement, tenloai text, trangthai text)";
        sqLiteDatabase.execSQL(qLoai);

        String qKhoan = "CREATE TABLE khoanthuchi(makhoan integer primary key autoincrement, tien, maloai interger)";
        sqLiteDatabase.execSQL(qKhoan);

        // data mẫu
        String ins1 = "INSERT INTO loai VALUES (1, 'Tiền xăng', 'chi'), (2, 'Tiền lương', 'thu'), (3, 'Tiền ăn sáng', 'chi'), (4, 'Tiền thưởng', 'thu')";
        sqLiteDatabase.execSQL(ins1);
        String ins2 = "INSERT INTO khoanthuchi VALUES (1, 5000, 2), (2, 15000, 3), (3, 1000, 1)";
        sqLiteDatabase.execSQL(ins2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            String dLoai = "DROP TABLE IF EXISTS loai";
            sqLiteDatabase.execSQL(dLoai);
            String dKhoan = "DROP TABLE IF EXISTS khoanthuchi";
            sqLiteDatabase.execSQL(dKhoan);
            onCreate(sqLiteDatabase);
        }
    }


}
