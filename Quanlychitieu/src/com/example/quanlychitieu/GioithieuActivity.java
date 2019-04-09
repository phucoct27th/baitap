package com.example.quanlychitieu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class GioithieuActivity extends ActionBarActivity {
	TextView tv;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gioithieu);
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		tv = (TextView) findViewById(R.id.tvvvv);
		String a = "+ Các chức năng chính của phần mềm:" + "\n"
				+ "- Có 3 Tab chức năng chính: Thu Chi, Thống Kê, Cài Đặt" + "\n"
				+ "- Thống kê: Tiền mặt, tiền tiết kiệm, thẻ tín dụng và số dư" + "\n"
				+ "- Thống kê chi tiết" + "\n"
				+ "- Hôm nay" + "\n"
				+ "- Tháng" + "\n"
				+ "- Năm" + "\n"
				+ "- Xem lịch sử giao dịch" + "\n"
				+ "- Thêm giao dịch..." + "\n"
				+ "+ Liên Hệ:" + "\n"				
				+ "- Email: Minhthuvlt@gmail.com";
		tv.setText(a);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gioithieu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
