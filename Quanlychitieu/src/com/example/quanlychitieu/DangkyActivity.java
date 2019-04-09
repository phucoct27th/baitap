package com.example.quanlychitieu;

import java.util.ArrayList;

import lop_dangky.DangKy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import du_lieu.BaseActivity;
import du_lieu.DatabaseHandler;

public class DangkyActivity extends BaseActivity {
	 DatabaseHandler db = new DatabaseHandler(DangkyActivity.this);
	    // dangky
	    EditText ed1, ed2, ed3;
	    Button bt;
	    DatabaseHandler helper = new DatabaseHandler(this);
	    ArrayList<DangKy> arrayDangKy;
	    // dangnhap
	    EditText ed, ed4;
	    Button bton;
	    // dangnhap
	    public static final String name = "pre";
	    final Context context = this;
	    ArrayList<DangKy> arrayList = new ArrayList<DangKy>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dangky); ed1 = (EditText) findViewById(R.id.eddk1);
        ed2 = (EditText) findViewById(R.id.eddk2);
        ed3 = (EditText) findViewById(R.id.eddk3);
        bt = (Button) findViewById(R.id.btdk);
        // ///
        ed = (EditText) findViewById(R.id.ed);
        ed4 = (EditText) findViewById(R.id.ed1);
        bton = (Button) findViewById(R.id.bt);
        arrayDangKy = db.getAllDangKy();
        final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        tab.setup();
        TabHost.TabSpec spec;
        // Tạo tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Đăng nhập");
        tab.addTab(spec);
        // Tạo tab2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Đăng ký");
        tab.addTab(spec);
        tab.setCurrentTab(0);
        // Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                DangKy dk = new DangKy(ed1.getText().toString(), ed2.getText()
                        .toString(), ed3.getText().toString());
                if (ed1.getText().toString().equals("")
                        || ed2.getText().toString().equals("")
                        || ed3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Dữ liệu các ô không được để trống",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    arrayDangKy.add(dk);
                    db.addData_DangKy(dk);
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                }

            }
        });
        //

        bton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DatabaseHandler database = new DatabaseHandler(context);
                arrayList = database.getAllDangKy();
                if (ed.getText().toString().equals("")
                        || ed4.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Tên đăng nhập và mật khẩu không được trống",
                            Toast.LENGTH_SHORT).show();
                } else {

                }
                for (int i = 0; i < arrayList.size(); i++) {
                    DangKy in = arrayList.get(i);
                    String u = in.getTenDangnhap();
                    String p = in.getMatKhau();
                    if (ed.getText().toString().equals(u)
                            && ed4.getText().toString().equals(p)) {
                        Toast.makeText(context, "Đăng nhập thành công",
                                Toast.LENGTH_SHORT).show();
                        Intent intt = new Intent(DangkyActivity.this,
                                MainActivity.class);
                        startActivity(intt);
                        savePreferences(u, p);
                        ed.setText(u);
                        ed4.setText(p);
                        
                    } else {
                        Toast.makeText(context, "Đăng nhập không thành công",
                                Toast.LENGTH_SHORT).show();
                        ed.setText("");
                        ed4.setText("");
                    }
                }
            }
        });
    }

    protected void savePreferences(String uSname, String pass) {
        SharedPreferences share = getSharedPreferences(name,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("a", uSname);
        editor.putString("b", pass);
        editor.commit();

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dangky, menu);
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
