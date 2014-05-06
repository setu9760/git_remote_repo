package com.setu.eO_GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.setu.EasyOrder.*;
import com.setu.eO_Adapters.ImageAdap;
import com.setu.eO_Logic.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
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

	// private final String link =
	// "http://androidphpmysql.comlu.com/easyorder/";

	private ImageAdap imageadap;
	private GridView gridview;
	private AlertDialog.Builder dialog1;
	private Button btncheckout;
	private Button btnresetorder;
	private TextView txtorderstatus;
	private Double subtotal = 0.0;
	private Double total = 0.0;
	private Double taxes = 0.0;

	public static List<Order> orderlist = new ArrayList<Order>();

	DBhelper db;
	Order order;

	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);

		db = new DBhelper(this);
		@SuppressWarnings("unused")
		final InitDB init = new InitDB(Second_activity.this);
		gridview = (GridView) findViewById(R.id.gridview1);
		btncheckout = (Button) findViewById(R.id.btncheckout);
		btnresetorder = (Button) findViewById(R.id.btnresetorder);
		txtorderstatus = (TextView) findViewById(R.id.txtorderstatus);
		setorderstatus(this.subtotal);

		btncheckout.setOnClickListener(this);
		btnresetorder.setOnClickListener(this);
		newthread newthread = new newthread();
		newthread.execute();
		gridview.setOnItemClickListener(this);

		btncheckout.setEnabled(false);
		btnresetorder.setEnabled(false);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(Second_activity.this, Third_activity.class);

		String item = Splash_activity.sel_list.get(position);// selectionlist.get(position).get_item();
		Bundle bundle = new Bundle();
		bundle.putInt("KEY_P_POSITION", ++position);
		bundle.putString("KEY_ITEM", item);
		intent.putExtras(bundle);
		startActivityForResult(intent, 1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Alert");
			alert.setMessage("Your order will reset." + "\n" + "Are you sure?");
			alert.setIcon(R.drawable.warning);
			alert.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							orderlist.clear();
							btncheckout.setEnabled(false);
							btnresetorder.setEnabled(false);
							setorderstatus(subtotal = 0.0);
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
		case R.id.btncheckout:
			try {
				Intent intent = new Intent(this, Checkout_activity.class);
				Bundle bundle = new Bundle();
				bundle.putDouble("key_total", this.total);
				bundle.putDouble("key_subtotal", this.subtotal);
				bundle.putDouble("key_taxes", this.taxes);
				intent.putExtras(bundle);
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
			btncheckout.setEnabled(true);
			Log.i("Setu", "price is: " + price);

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
		dialog.setIcon(R.drawable.warning);
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				orderlist.clear();
				btncheckout.setEnabled(false);
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

	public void setorderstatus(Double subttotal) {
		if (orderlist.size() == 1) {
			this.subtotal = 0.0;
		}
		this.subtotal += subttotal;
		this.taxes = (this.subtotal * VAT);
		this.total = (this.subtotal + this.taxes);

		DecimalFormat formmater = new DecimalFormat("##0.00");
		String subtotal, total, tax;
		subtotal = formmater.format(this.subtotal);
		tax = formmater.format(this.taxes);
		total = formmater.format(this.total);
		Log.i("Setu", "items in the order now: " + orderlist.size());
		String orderstatus = "Order Summary " + "\n\n" + "Subtotal: £"
				+ subtotal + "\n" + "VAT: £" + tax + "\n" + "Total: £" + total;
		if (this.subtotal != 0) {
			btncheckout.setEnabled(true);
			btnresetorder.setEnabled(true);
		} else {
			btncheckout.setEnabled(false);
			btnresetorder.setEnabled(false);
		}
		txtorderstatus.setText(orderstatus);

	}

	private class newthread extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(Second_activity.this);
			pd.setTitle("Please Wait");
			pd.setMessage("Fetching data");
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			imageadap = new ImageAdap(Second_activity.this,
					Splash_activity.sel_list, Splash_activity.sel_id);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			gridview.setAdapter(imageadap);
			pd.dismiss();
		}

	}
}
