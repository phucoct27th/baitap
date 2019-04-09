package com.example.quanlychitieu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandle {

	private static final String DATABASE_NAME = "quanlychitieu";
	private static final int DATABASE_ver = 1;
	static final String TABLE_NAME = "giaodich";
	static final String TABLE_NAME2 = "thuchi";
	static final String COLUM_ID = "id";
	static final String COLUM_KHOANTHUKHOANCHI = "khoanthukhoanchi";
	static final String COLUM_PHANLOAI = "phanloai";
	static final String COLUM_TAIKHOAN = "taikhoan";
	static final String COLUM_LOAIGIAODICH = "loaigiaodich";
	static final String COLUM_SOTIEN = "sotien";
	static final String COLUM_LYDO = "lydo";
	static final String COLUM_PHANNHOM = "phanhom";
	static final String COLUM_NGAYGIAODICH = "ngaygiaodich";
	static final String COLUM_NGAY = "ngay";
	static final String COLUM_THANG = "thang";
	static final String COLUM_NAM = "nam";
	private Context _context;
	private static Context context;
	static SQLiteDatabase db;
	SQLiteDatabase db1;
	static OpenHelper openHelper;

	public DatabaseHandle(Context context) {
		DatabaseHandle.context = context;

	}

	public DatabaseHandle open() throws SQLException {
		openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();

		return this;

	}

	public void close() {
		openHelper.close();
	}

	public long themkhoanthuchi(String khoanthukhoanchi, String phanloai) {
		ContentValues cv = new ContentValues();
		cv.put(COLUM_KHOANTHUKHOANCHI, khoanthukhoanchi);
		cv.put(COLUM_PHANLOAI, phanloai);

		return db.insert(TABLE_NAME2, null, cv);

	}

	public void Delete(String phanloai, String khoanthukhoanchi) {
		db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE " + COLUM_PHANLOAI
				+ "='" + phanloai + "'" + " AND " + COLUM_KHOANTHUKHOANCHI
				+ " = '" + khoanthukhoanchi + "'");
		db.close();
	}

	public void Deletels(String ngaygiaodich, String lydo, String sotien,
			String taikhoan) {

		db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUM_NGAYGIAODICH
				+ "='" + ngaygiaodich + "'" + " AND " + COLUM_LYDO + " = '"
				+ lydo + "'" + " AND " + COLUM_SOTIEN + " = '" + sotien + "'"
				+ " AND " + COLUM_TAIKHOAN + " = '" + taikhoan + "'");
		db.close();
	}

	public long themgiaodich(String taikhoan, String loaigiaodich,
			String sotien, String lydo, String phannhom, String ngaygiaodich,
			String ngay, String thang, String nam) {
		ContentValues cv1 = new ContentValues();
		cv1.put(COLUM_TAIKHOAN, taikhoan);
		cv1.put(COLUM_LOAIGIAODICH, loaigiaodich);
		cv1.put(COLUM_SOTIEN, sotien);
		cv1.put(COLUM_LYDO, lydo);
		cv1.put(COLUM_PHANNHOM, phannhom);
		cv1.put(COLUM_NGAYGIAODICH, ngaygiaodich);
		cv1.put(COLUM_NGAY, ngay);
		cv1.put(COLUM_THANG, thang);
		cv1.put(COLUM_NAM, nam);

		return db.insert(TABLE_NAME, null, cv1);

	}

	static class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_ver);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table " + TABLE_NAME + " ( " + COLUM_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
					+ COLUM_TAIKHOAN + " text," + COLUM_LOAIGIAODICH + " text,"
					+ COLUM_SOTIEN + " text," + COLUM_LYDO + " text,"
					+ COLUM_PHANNHOM + " text," + COLUM_NGAYGIAODICH + " text,"
					+ COLUM_NGAY + " text," + COLUM_THANG + " text,"
					+ COLUM_NAM + " text);"

			);
			db.execSQL("create table " + TABLE_NAME2 + " ( " + COLUM_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
					+ COLUM_KHOANTHUKHOANCHI + " text,"

					+ COLUM_PHANLOAI + " text);"

			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
			onCreate(db);
		}

	}

	public List<String> getAllNames(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT " + COLUM_PHANLOAI + " FROM "
				+ TABLE_NAME2 + " WHERE " + COLUM_KHOANTHUKHOANCHI + " = "
				+ "'" + thuchi + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getloggiaodich(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT distinct " + COLUM_PHANNHOM + " FROM "
				+ TABLE_NAME + " WHERE " + COLUM_LOAIGIAODICH + " = " + "'"
				+ phanloai + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getloggiaodichhomnay(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();

		String selectQuery = "SELECT distinct "
				+ COLUM_PHANNHOM
				+ " FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUM_LOAIGIAODICH
				+ " = "
				+ "'"
				+ phanloai
				+ "' and ngay = strftime('%d','now') and  thang = strftime('%m','now') and nam =strftime('%Y','now')";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();

		return names;
	}

	public List<String> getloggiaodichthangnay(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT distinct "
				+ COLUM_PHANNHOM
				+ " FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUM_LOAIGIAODICH
				+ " = "
				+ "'"
				+ phanloai			//trả về chuỗi đã được định dang.%m − Tháng (01 tới 12),%Y − Năm bao gồm Thế kỷ,Ngày trong tháng (01 tới 31)
				+ "'  and  thang = strftime('%m','now') and nam =strftime('%Y','now')";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		cursor.close();

		return names;
	}

	public List<String> getloggiaodichnamnay(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();

		String selectQuery = "SELECT distinct " + COLUM_PHANNHOM + " FROM "
				+ TABLE_NAME + " WHERE " + COLUM_LOAIGIAODICH + " = " + "'"
				+ phanloai + "' and nam =strftime('%Y','now')";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getloggiaodichnamnay1(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT distinct " + COLUM_PHANNHOM + " FROM "
				+ TABLE_NAME + " WHERE " + COLUM_LOAIGIAODICH + " = " + "'"
				+ phanloai + "' and nam =strftime('%Y','now')";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getloggiaodichthangnay1(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT distinct " //Câu lệnh này dùng để loại bỏ những giá trị giống nhau và chỉ show ra 1 giá trị .
				+ COLUM_PHANNHOM
				+ " FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUM_LOAIGIAODICH
				+ " = "
				+ "'"
				+ phanloai
				+ "'  and  thang = strftime('%m','now') and nam =strftime('%Y','now')";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		cursor.close();

		return names;
	}

	public List<String> getloggiaodichhomnay1(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();

		String selectQuery = "SELECT distinct "
				+ COLUM_PHANNHOM
				+ " FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUM_LOAIGIAODICH
				+ " = "
				+ "'"
				+ phanloai
				+ "' and ngay = strftime('%d','now') and  thang = strftime('%m','now') and nam =strftime('%Y','now')";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getlognam(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  phanhom FROM giaodich WHERE nam = '2015'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		cursor.close();
		return names;
	}

	public List<String> getloggiaodich1(String phanloai) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT distinct " + COLUM_PHANNHOM + " FROM "
				+ TABLE_NAME + " WHERE " + COLUM_LOAIGIAODICH + " = " + "'"
				+ phanloai + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getsotien(String phannhom, String loaigiaodich) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT sum(" + COLUM_SOTIEN + ") FROM "
				+ TABLE_NAME + " where " + COLUM_PHANNHOM + " = '" + phannhom
				+ "' AND " + COLUM_LOAIGIAODICH + " = '" + loaigiaodich + "'";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();

		return names;
	}

	public List<String> getsotienhomnay(String phannhom, String loaigiaodich) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  sum("
				+ COLUM_SOTIEN
				+ ") FROM "
				+ TABLE_NAME
				+ " where "
				+ COLUM_PHANNHOM
				+ " = '"
				+ phannhom
				+ "' and "
				+ COLUM_LOAIGIAODICH
				+ " = '"
				+ loaigiaodich
				+ "' and ngay = strftime('%d','now') and  thang = strftime('%m','now') and nam =strftime('%Y','now');";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getsotienthangnay(String phannhom, String loaigiaodich) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  sum("
				+ COLUM_SOTIEN
				+ ") FROM "
				+ TABLE_NAME
				+ " where "
				+ COLUM_PHANNHOM
				+ " = '"
				+ phannhom
				+ "' and "
				+ COLUM_LOAIGIAODICH
				+ " = '"
				+ loaigiaodich
				+ "'  and  thang = strftime('%m','now') and nam =strftime('%Y','now');";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> getsotiennamnay(String phannhom, String loaigiaodich) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  sum(" + COLUM_SOTIEN + ") FROM "
				+ TABLE_NAME + " where " + COLUM_PHANNHOM + " = '" + phannhom
				+ "' and " + COLUM_LOAIGIAODICH + " = '" + loaigiaodich
				+ "'  and nam =strftime('%Y','now');";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<Lichsu> lichsugiaodich() {
		// TODO Auto-generated method stub
		ArrayList<Lichsu> lichsugiaodich = new ArrayList<Lichsu>();
		String selectQuery = "SELECT " + COLUM_NGAYGIAODICH + "," + COLUM_LYDO
				+ "," + COLUM_SOTIEN + "," + COLUM_TAIKHOAN + " FROM "
				+ TABLE_NAME;
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				Lichsu contacts = new Lichsu();
				contacts.setTime(cursor.getString(0));
				contacts.setPhanloai(cursor.getString(1));
				contacts.setSotien(cursor.getString(2));
				contacts.setTaikhoan(cursor.getString(3));

				lichsugiaodich.add(contacts);

			} while (cursor.moveToNext());
		}
		cursor.close();
		return lichsugiaodich;
	}

	public List<String> getphanloai(String khoanthuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT " + COLUM_PHANLOAI + " FROM  "
				+ TABLE_NAME2 + " where " + COLUM_KHOANTHUKHOANCHI + " = "
				+ "'" + khoanthuchi + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> tongtien(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT sum ( " + COLUM_SOTIEN + " ) FROM "
				+ TABLE_NAME + " where " + COLUM_LOAIGIAODICH + "= '" + thuchi
				+ "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> tongtienhomnay(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();

		String selectQuery = "SELECT sum ( "
				+ COLUM_SOTIEN
				+ " ) FROM "
				+ TABLE_NAME
				+ " where "
				+ COLUM_LOAIGIAODICH
				+ "= '"
				+ thuchi
				+ "' and ngay = strftime('%d','now') and  thang = strftime('%m','now') and nam =strftime('%Y','now');";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> tongtienthangnay(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT sum ( "
				+ COLUM_SOTIEN
				+ " ) FROM "
				+ TABLE_NAME
				+ " where "
				+ COLUM_LOAIGIAODICH
				+ "= '"
				+ thuchi
				+ "' and  thang = strftime('%m','now') and nam =strftime('%Y','now');";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> tongtiennamnay(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT sum ( " + COLUM_SOTIEN + " ) FROM "
				+ TABLE_NAME + " where " + COLUM_LOAIGIAODICH + "= '" + thuchi
				+ "' and nam =strftime('%Y','now');";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		cursor.close();
		return names;
	}

	public List<String> taikhoan(String thuchi) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT sum ( " + COLUM_SOTIEN + " ) FROM "
				+ TABLE_NAME + " where " + COLUM_TAIKHOAN + "= '" + thuchi
				+ "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.getCount() > 0) {
			if (cursor != null & cursor.moveToFirst()) {
				do {
					names.add(cursor.getString(0));

				} while (cursor.moveToNext());
			}
			cursor.close();
		}
		return names;

	}

	public boolean kiemtra(String spinthuchi, String kiemtra) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  COUNT(*) from " + TABLE_NAME2 + " where "
				+ COLUM_PHANLOAI + "=" + "'" + kiemtra + "'" + " AND "
				+ COLUM_KHOANTHUKHOANCHI + "=" + "'" + spinthuchi + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				names.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		int so = Integer.parseInt(names.get(0));
		if (so == 0) {
			return true;
		} else {
			return false;
		}
	}

}
