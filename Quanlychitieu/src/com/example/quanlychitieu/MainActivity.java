package com.example.quanlychitieu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	@Override

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
   
	    final TabHost tabHost = getTabHost();
	
	    TabSpec thuchi = tabHost.newTabSpec("Thu Chi");
	    thuchi.setIndicator("Thu Chi", getResources().getDrawable(R.drawable.ic_launcher));
	    Intent i = new Intent(MainActivity.this, ThuChiMainActivity.class);
	    thuchi.setContent(i);

	    TabSpec thongke = tabHost.newTabSpec("Thống Kê");

	    thongke.setIndicator("Thống Kê", getResources().getDrawable(R.drawable.ic_launcher));
	    Intent o = new Intent(this, ThongKeMainActivity.class);
	    thongke.setContent(o);

	    TabSpec caidat = tabHost.newTabSpec("Cài Đặt");
	    caidat.setIndicator("Cài Đặt", getResources().getDrawable(R.drawable.ic_launcher));
	    Intent p = new Intent(this, CaiDatMainActivity.class);
	    caidat.setContent(p);

	    tabHost.addTab(thuchi); 
	    tabHost.addTab(thongke); 
	    tabHost.addTab(caidat); 
	   tabHost.setCurrentTab(0);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		  switch (item.getItemId()) {
		    case R.id.thoat:
		        about();
		        return true;
		    default:
		        return super.onOptionsItemSelected(item);
		    }
		 
	}
	 public void about(){
		 System.exit(0);
	  }
}
