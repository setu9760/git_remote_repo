package com.setu.eO_Adapters;

import java.util.ArrayList;
import java.util.List;

import com.setu.EasyOrder.R;
import com.setu.eO_GUI.Second_activity;
import com.setu.eO_Logic.Order;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Order_adapter extends BaseAdapter {
	private final Context context;
	public static List<String> product = null;
	public static List<Double> price = null;
	private LayoutInflater inflater;

	public Order_adapter(Context context) {
		product = new ArrayList<String>();
		price = new ArrayList<Double>();
		this.context = context;
		this.inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (Order order : Second_activity.orderlist) {
			Log.i("Setu", "inside for each loop");
			product.add(order.getProduct());
			price.add(order.getPrice());
		}
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		viewholder holder;
		Log.i("Setu", "getview called");
		if (convertview == null) {
			convertview = inflater.inflate(R.layout.view_listview2, null);
			holder = new viewholder();
			holder.product = (TextView) convertview.findViewById(R.id.product);
			holder.price = (TextView) convertview.findViewById(R.id.price);
			convertview.setTag(holder);
		} else {
			holder = (viewholder) convertview.getTag();
		}
		holder.product.setText(product.get(position));
		holder.price.setText("£" + String.valueOf(price.get(position)));

		return convertview;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return product.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static class viewholder {
		TextView product;
		TextView price;
	}

}
