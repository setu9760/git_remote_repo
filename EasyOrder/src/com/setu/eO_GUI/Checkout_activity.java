package com.setu.eO_GUI;

import com.setu.eO_Logic.Order;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Checkout_activity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		for (Order order : Second_activity.orderlist) {
			Log.i("Setu", "order items " + order.getProduct());
		}
	}
}
