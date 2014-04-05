package com.setu.eO_GUI;

import java.text.DecimalFormat;

import com.setu.EasyOrder.R;
import com.setu.eO_Adapters.Order_adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;

public class Checkout_activity extends Activity implements
		OnItemLongClickListener, android.view.View.OnClickListener {

	private Order_adapter adapter;
	private ListView listview;
	private Button btnpaynow;
	private TextView tspayment;
	private Double subtotal = 0.0;
	private Double total = 0.0;
	private Double taxes = 0.0;
	private final Double VAT = 0.065;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_screen);

		Bundle bundle = getIntent().getExtras();
		total = bundle.getDouble("key_total");

		listview = (ListView) findViewById(R.id.listview2);
		btnpaynow = (Button) findViewById(R.id.btnpaynow);
		tspayment = (TextView) findViewById(R.id.tspayment);
		tspayment.setText(String.valueOf(total));

		newthread thread = new newthread();
		thread.execute();

		btnpaynow.setOnClickListener(this);
		listview.setOnItemLongClickListener(this);

		Toast.makeText(Checkout_activity.this,
				"Long click on product to delete from the order",
				Toast.LENGTH_SHORT).show();
	}

	public class newthread extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			adapter = new Order_adapter(Checkout_activity.this);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			listview.setAdapter(adapter);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		final int deleteposition = position;
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Confirm");
		alert.setMessage("Are you sure you want to delete " + "\""
				+ Order_adapter.product.get(deleteposition) + "\""
				+ "from your order?");
		alert.setIcon(R.drawable.warning);
		alert.setPositiveButton("Delete", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Order_adapter.product.remove(deleteposition);
				Order_adapter.price.remove(deleteposition);
				Second_activity.orderlist.remove(deleteposition);
				setorderstatus();
				adapter.notifyDataSetChanged();
			}
		});
		alert.setNeutralButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				/*
				 * This button does nothing
				 */

			}
		});
		alert.show();
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnpaynow:
			Intent intent = new Intent(Checkout_activity.this,
					Payment_activity.class);
			Bundle bundle = new Bundle();
			bundle.putDouble("key_total", this.total);
			bundle.putDouble("key_taxes", this.taxes);
			bundle.putDouble("key_subtotal", this.subtotal);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		}

	}

	public void setorderstatus() {
		this.subtotal = 0.0;
		for (int i = 0; i < Order_adapter.price.size(); i++) {
			this.subtotal += Order_adapter.price.get(i);
		}
		this.taxes = (this.subtotal * VAT);
		this.total = (this.subtotal + this.taxes);
		DecimalFormat formmater = new DecimalFormat("##0.00");
		if (this.total == 0) {
			btnpaynow.setEnabled(false);
		}
		tspayment.setText(" £"+String.valueOf(formmater.format(total)));
	}
	public void finalbundle(){
		
	}
}
