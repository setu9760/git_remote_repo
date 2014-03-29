package com.setu.eO_Adapters;

import java.util.List;

import com.setu.EasyOrder.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdap extends BaseAdapter {
	private Context context;
	private final String[] items;
	private final Integer[] id;
	private LayoutInflater inflator;

	public ImageAdap(Context context, List<String> items, List<Integer> id) {
		this.inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.items = new String[items.size()];
		this.id = new Integer[id.size()];
		for (int i = 0; i < items.size(); i++) {
			this.items[i] = items.get(i);
			this.id[i] = id.get(i);
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("Setu", "get view called");
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflator.inflate(R.layout.view_gridview1, null);
			holder = new ViewHolder();
			Log.i("Setu", "using holder object to inflate custome gridview");
			holder.image = (ImageView) convertView.findViewById(R.id.grid_img);
			holder.item = (TextView) convertView.findViewById(R.id.grid_label);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.item.setText(this.items[position]);
		Integer tempid = id[position];
		switch (tempid) {
		case 1:
			holder.image.setImageResource(R.drawable.pizza);
			break;
		case 3:
			holder.image.setImageResource(R.drawable.dessert);
			break;
		case 4:
			holder.image.setImageResource(R.drawable.drinks);
			break;
		default:
			holder.image.setImageResource(R.drawable.ic_launcher);
			break;
		}

		return convertView;

	}

	@Override
	public int getCount() {

		return items.length;
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
		TextView item;
	}

}
