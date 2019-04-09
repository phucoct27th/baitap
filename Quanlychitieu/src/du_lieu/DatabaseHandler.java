package du_lieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import lop_dangky.DangKy;


public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DangKy";
    public static final int DATABASE_VERSION = 1;

    public void doTableDangKy(SQLiteDatabase db) {
        String sSQL = "CREATE  TABLE tbDangKy (TenDangNhap TEXT PRIMARY KEY  NOT NULL , MatKhau TEXT,ReMatKhau TEXT)";
        db.execSQL(sSQL);
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        doTableDangKy(db);

    }

    public void addDataDangKy( String tenDangnhap, String matKhau,String reMatkhau) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            String sSQL = "INSERT INTO tbDangKy (TenDangNhap,MatKhau,ReMatKhau) VALUES ("
                    + tenDangnhap + "," + matKhau +","+ reMatkhau+")";

            db.execSQL(sSQL);
            // insert rows
            db.setTransactionSuccessful();
            // Log.i("insert row data", "name: " + name);
        } catch (SQLiteException e2) {
            // report problem
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<DangKy> getAllDangKy() {
        ArrayList<DangKy> listDangKy = new ArrayList<DangKy>();
        // Select All Query
        String sSQL = "SELECT  * FROM tbDangKy";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sSQL, null);
        if (cursor.moveToFirst()) {
            do {
                DangKy dk = new DangKy();
                dk.setTenDangnhap(cursor.getString(0));
                dk.setMatKhau(cursor.getString(1));
                dk.setReMatkhau(cursor.getString(2));
                // Adding contact to list
                listDangKy.add(dk);
            } while (cursor.moveToNext());
        }
        return listDangKy;
    }

    public void addData_DangKy(DangKy dk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenDangNhap", dk.getTenDangnhap());
        values.put("MatKhau", dk.getMatKhau());
        values.put("ReMatKhau", dk.getReMatkhau());
        // Inserting Row
        db.insert("tbDangKy", null, values);
        db.close(); // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
