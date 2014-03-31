package com.setu.eO_GUI;

import com.setu.EasyOrder.R;
import com.setu.EasyOrder.R.id;
import com.setu.eO_Logic.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	private EditText txtusername;
	private EditText txtpassword;
	private Button btnlogin;
	private Button btnreset;
	private Button btnregister;
	private CheckBox chkremme;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		chkremme = (CheckBox) findViewById(R.id.chkremme);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnregister = (Button) findViewById(R.id.btnregister);
		btnreset = (Button) findViewById(R.id.btnreset);
		chkremme = (CheckBox) findViewById(R.id.chkremme);

		btnlogin.setOnClickListener(this);
		btnregister.setOnClickListener(this);
		btnreset.setOnClickListener(this);

		progress = new ProgressDialog(this);
		progress.setTitle("Please wait...");
		progress.setMessage("Authenticating Login..");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			newtask newtask = new newtask();
			newtask.execute();

			Intent intent = new Intent(MainActivity.this, Second_activity.class);
			startActivity(intent);
			break;
		case R.id.btnregister:
			try {
				Intent intent2 = new Intent(MainActivity.this,
						Expandable_activity.class);
				startActivity(intent2);
			} catch (Exception e) {
				Log.e("Setu", "error while calling btnregister");
			}

			break;
		case R.id.btnreset:

			break;

		}

	}

	class newtask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progress.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progress.dismiss();
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

	}

}
