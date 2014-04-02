package com.setu.eO_GUI;

import java.util.ArrayList;
import java.util.List;

import com.setu.EasyOrder.*;
import com.setu.eO_Adapters.Products_adapter;
import com.setu.eO_Logic.All_products;
import com.setu.eO_Logic.DBhelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Third_activity extends Activity implements OnItemClickListener {

	private ListView listview;
	private List<All_products> productlist;
	private List<String> p_item = new ArrayList<String>();
	private List<Double> p_price = new ArrayList<Double>();
	private List<Integer> p_veg = new ArrayList<Integer>();
	private Products_adapter product_adapter;

	DBhelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);
		db = new DBhelper(this);

		Bundle bundle = getIntent().getExtras();
		int position = bundle.getInt("KEY_P_POSITION");

		productlist = db.get_one_catagory_product(position);

		for (All_products all_products : productlist) {
			p_price.add(all_products.get_price());
			p_item.add(all_products.get_item());
			p_veg.add(all_products.get_veg());
		}
		for (int i = 0; i < p_price.size(); i++) {
			Log.i("Setu", "price " + i + " is " + p_price.get(i) + "   "
					+ p_item.get(i) + " -- " + p_veg.get(i));
		}
		listview = (ListView) findViewById(R.id.listview1);
		product_adapter = new Products_adapter(this, p_item, p_price, p_veg);
		listview.setAdapter(product_adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("key_product", p_item.get(position));
		bundle.putDouble("key_price", p_price.get(position));
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent);
		super.onBackPressed();
	}

}
