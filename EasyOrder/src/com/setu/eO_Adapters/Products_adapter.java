package com.setu.eO_Adapters;

import java.util.ArrayList;
import java.util.List;

import com.setu.EasyOrder.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Products_adapter extends BaseAdapter {
	private Context context;
	private List<String> products = new ArrayList<String>();
	private List<Double> price = new ArrayList<Double>();
	private List<Integer> _veg = new ArrayList<Integer>();
	private LayoutInflater inflater;

	public Products_adapter(Context context, List<String> p_item,
			List<Double> p_price, List<Integer> p_veg) {
		this.context = context;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.products = p_item;
		this.price = p_price;
		this._veg = p_veg;
		Log.i("Setu", "size " + this.products.size() + " " + this.price.size());

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.view_listview1, null);
			holder = new ViewHolder();
			holder.product = (TextView) convertView
					.findViewById(R.id.list_product);
			holder.price = (TextView) convertView.findViewById(R.id.list_price);
			convertView.setTag(holder);
			holder.image = (ImageView) convertView
					.findViewById(R.id.list_vegimage);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.product.setText(this.products.get(position));
		holder.price.setText( String.valueOf(this.price.get(position)));
		Integer temp_veg = _veg.get(position);
		switch (temp_veg) {
		case 0:
			holder.image.setImageResource(R.drawable.vege_img);
			break;
		case 1:
			holder.image.setImageResource(R.drawable.non_veg_img);
			break;
		}
		return convertView;
	}
	@Override
	public int getCount() {

		return products.size();
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	private static class ViewHolder {
		ImageView image;
		TextView product;
		TextView price;
	}

}
