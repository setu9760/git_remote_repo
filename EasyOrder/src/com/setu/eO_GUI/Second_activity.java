package com.setu.eO_GUI;

import java.util.ArrayList;
import java.util.List;

import com.setu.EasyOrder.*;
import com.setu.eO_Adapters.ImageAdap;
import com.setu.eO_Logic.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Second_activity extends Activity implements OnItemClickListener,
		android.view.View.OnClickListener {

	private ImageAdap imageadap;
	private GridView gridview;
	private AlertDialog.Builder dialog1;
	private Button btnsubmitorder;
	private Button btnresetorder;

	private List<Selection> selectionlist;
	private List<Integer> sel_id = new ArrayList<Integer>();
	private List<String> sel_list = new ArrayList<String>();
	DBhelper db;
	Order order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);

		db = new DBhelper(this);
		@SuppressWarnings("unused")
		final InitDB init = new InitDB(Second_activity.this);

		selectionlist = db.getallselection();
		for (Selection selection : selectionlist) {
			sel_id.add(selection.get_id());
			sel_list.add(selection.get_item());
			Log.i("Setu", "ID count: " + sel_id.size());
		}

		gridview = (GridView) findViewById(R.id.gridview1);
		btnsubmitorder = (Button) findViewById(R.id.btnsubmitorder);
		btnresetorder = (Button) findViewById(R.id.btnresetorder);

		btnsubmitorder.setOnClickListener(this);
		btnresetorder.setOnClickListener(this);
		newthread newthread = new newthread();
		newthread.execute();
		gridview.setOnItemClickListener(this);

		btnsubmitorder.setEnabled(false);
		btnresetorder.setEnabled(false);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(Second_activity.this, Third_activity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("KEY_P_POSITION", ++position);
		Toast.makeText(this, position + "   ", Toast.LENGTH_LONG).show();
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);

		btnsubmitorder.setEnabled(true);
		btnresetorder.setEnabled(true);

		/*
		 * need to add some logic here perform some programming on he order
		 * object
		 */
		// order.additem(temp);
		// Log.i("Setu", "Items in the order... " + order.getsize());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnsubmitorder:

			break;
		case R.id.btnresetorder:
			dialog1 = new AlertDialog.Builder(this);
			String title = "Confirm";
			String message = "Are you sure to reset the order?";
			Boolean isCancelable = true;
			dialogbox(dialog1, title, message, isCancelable);
			break;
		}
	}

	/**
	 * @param dialog
	 * @param title
	 * @param message
	 * @param isCancelable
	 */
	public void dialogbox(Builder dialog, String title, String message,
			Boolean isCancelable) {
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setCancelable(isCancelable);
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				/*
				 * 
				 * need to add some logic here
				 */
				btnsubmitorder.setEnabled(false);
				btnresetorder.setEnabled(false);
			}
		});
		dialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// this button does nothing
			}
		});
		AlertDialog alert = dialog.create();
		alert.show();
	}

	class newthread extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			imageadap = new ImageAdap(Second_activity.this, sel_list, sel_id);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			gridview.setAdapter(imageadap);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(0, resultCode, data);

		Bundle bundle = data.getExtras();
		String item = bundle.getString("key_product");
		Double price = bundle.getDouble("key_price");
		Toast.makeText(this, "product: " + item + " price " + price,
				Toast.LENGTH_LONG).show();
	}
}
