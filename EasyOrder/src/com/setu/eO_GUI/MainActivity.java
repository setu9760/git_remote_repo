package com.setu.eO_GUI;

import com.setu.EasyOrder.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnlogin;
	private Button btnreset;
	private Button btnregister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnregister = (Button) findViewById(R.id.btnregister);
		btnreset = (Button) findViewById(R.id.btnreset);

		btnlogin.setOnClickListener(this);
		btnregister.setOnClickListener(this);
		btnreset.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			Intent intent = new Intent(MainActivity.this, Second_activity.class);
			startActivity(intent);
			break;
		case R.id.btnregister:
			Intent intent1 = new Intent(MainActivity.this, Spash_activity.class);
			startActivity(intent1);
			break;
		case R.id.btnreset:

			break;

		}

	}
}
