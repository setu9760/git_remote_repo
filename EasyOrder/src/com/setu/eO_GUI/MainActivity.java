package com.setu.eO_GUI;

import com.setu.EasyOrder.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnordernow;
	private Button btnchkorders;
	private Button btncontactus;
	private Button btnrateus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnordernow = (Button) findViewById(R.id.btnordernow);
		btnchkorders = (Button) findViewById(R.id.btnchkorders);
		btncontactus = (Button) findViewById(R.id.btncontactus);
		btnrateus = (Button) findViewById(R.id.btnrating);

		btnordernow.setOnClickListener(this);
		btnchkorders.setOnClickListener(this);
		btncontactus.setOnClickListener(this);
		btnrateus.setOnClickListener(this);

	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		finish();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnordernow:
			Intent intent = new Intent(MainActivity.this, Second_activity.class);
			startActivity(intent);
			break;
		case R.id.btnchkorders:

			break;
		case R.id.btncontactus:
			Intent intent2 = new Intent(MainActivity.this,
					Contactus_activity.class);
			startActivity(intent2);
			break;
		case R.id.btnrating:

			break;

		}

	}
}
