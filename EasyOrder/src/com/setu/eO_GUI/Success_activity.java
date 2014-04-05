package com.setu.eO_GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.setu.EasyOrder.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Success_activity extends Activity {

	private TextView lblsuccessmessage;
	private Button btnok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success_screen);

		lblsuccessmessage = (TextView) findViewById(R.id.lblsuccessmessage);
		btnok = (Button) findViewById(R.id.btnok);
		setmessage();

		btnok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Success_activity.this,
						MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});

	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(false);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		moveTaskToBack(false);
		return super.onKeyDown(keyCode, event);
	}

	public void setmessage() {
		File externalpath = Environment.getExternalStorageDirectory();
		File newfile = new File(externalpath, "order.txt");
		StringBuilder sb = new StringBuilder();
		sb.append("Summary" + System.getProperty("line.separator"));
		FileInputStream fileinputstream;
		try {
			fileinputstream = new FileInputStream(newfile);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileinputstream));

			String temp;
			while ((temp = bufferedReader.readLine()) != null) {
				sb.append(temp);
				sb.append(System.getProperty("line.separator"));
			}
			bufferedReader.close();
			fileinputstream.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		lblsuccessmessage.setText(sb.toString());
	}
}
