package com.setu.eO_GUI;

import java.text.DecimalFormat;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Second_activity extends Activity implements OnItemClickListener,
		android.view.View.OnClickListener {

	private final Double VAT = 0.065;

	private ImageAdap imageadap;
	private GridView gridview;
	private AlertDialog.Builder dialog1;
	private Button btnsubmitorder;
	private Button btnresetorder;
	private TextView txtorderstatus;
	private Double subtotal = 0.0;
	private Double total = 0.0;
	private Double taxes = 0.0;

	private List<Selection> selectionlist;
	private List<Integer> sel_id = new ArrayList<Integer>();
	private List<String> sel_list = new ArrayList<String>();

	public static List<Order> orderlist = new ArrayList<Order>();

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
		txtorderstatus = (TextView) findViewById(R.id.txtorderstatus);
		setorderstatus(subtotal);

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
		startActivityForResult(intent, 1);

		btnsubmitorder.setEnabled(true);
		btnresetorder.setEnabled(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Alert");
			alert.setMessage("Your order will reset." + "\n" + "Are you sure?");
			alert.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			alert.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							/*
							 * This button does nothing apart from closing the
							 * dialogbox
							 */
						}
					});
			alert.show();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnsubmitorder:
			try {
				Intent intent = new Intent(this, Checkout_activity.class);
				startActivity(intent);
			} catch (Exception e) {
				Log.e("Setu", "error in intent" + e);
			}
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			Log.i("Setu", "request code is:" + requestCode + "result code is: "
					+ resultCode);

			String item = bundle.getString("key_product");
			Double price = bundle.getDouble("key_price");
			Toast.makeText(this, item + " added in the order",
					Toast.LENGTH_LONG).show();
			btnresetorder.setEnabled(true);
			btnsubmitorder.setEnabled(true);
			setorderstatus(price);
		} else {

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
				 * need to add some logic here to delete the order from the
				 * order list still working on it on 31st March.
				 */
				orderlist.clear();
				btnsubmitorder.setEnabled(false);
				btnresetorder.setEnabled(false);
				setorderstatus(subtotal = 0.0);

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

	public void setorderstatus(Double subtotal) {
		this.subtotal += subtotal;
		this.taxes = (this.subtotal * VAT);
		this.total = (this.subtotal + this.taxes);

		DecimalFormat formmater = new DecimalFormat("##0.00");
		String subt, ttl, tax;
		subt = formmater.format(this.subtotal);
		tax = formmater.format(this.taxes);
		ttl = formmater.format(this.total);
		Log.i("Setu", "items in the order now: " + orderlist.size());
		String orderstatus = "Subtotal: £" + subt + "\n" + "VAT: £" + tax
				+ "\n" + "Total: £" + ttl;
		txtorderstatus.setText(orderstatus);
	}

	private class newthread extends AsyncTask<Void, Void, Void> {
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
}
