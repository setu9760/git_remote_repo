package com.setu.eO_GUI;

import com.setu.EasyOrder.R;
import com.setu.eO_Adapters.Order_adapter;
import com.setu.eO_Logic.Order;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Checkout_activity extends Activity implements
		OnItemLongClickListener, OnItemClickListener {

	private Order_adapter adapter;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_screen);

		listview = (ListView) findViewById(R.id.listview2);
		newthread thread = new newthread();
		thread.execute();
		listview.setOnItemClickListener(this);
		listview.setOnItemLongClickListener(this);

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
		alert.setTitle("Alert");
		alert.setMessage("Are you sure you want to delete " + "\""
				+ Order_adapter.product.get(deleteposition) + "\""
				+ "from your order?");
		alert.setPositiveButton("Delete", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Order_adapter.product.remove(deleteposition);
				Order_adapter.price.remove(deleteposition);
				Second_activity.orderlist.remove(deleteposition);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Toast.makeText(this, "Long click on the item to delete from the order",
				Toast.LENGTH_LONG).show();
	}

}
