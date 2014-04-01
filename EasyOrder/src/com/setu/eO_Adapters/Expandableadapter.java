package com.setu.eO_Adapters;

import java.util.HashMap;
import java.util.List;

import com.setu.EasyOrder.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class Expandableadapter extends BaseExpandableListAdapter {
	private Context context;
	private List<String> listparent;
	private HashMap<String, List<String>> listchild;

	public Expandableadapter(Context context, List<String> listparent,
			HashMap<String, List<String>> listchild) {
		this.context = context;
		this.listparent = listparent;
		this.listchild = listchild;
	}

	@Override
	public Object getChild(int parentPosition, int childPosition) {

		return this.listchild.get(this.listparent.get(parentPosition)).get(
				childPosition);
	}

	@Override
	public long getChildId(int parentPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public View getChildView(int parentPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(parentPosition,
				childPosition);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expandable_child, null);
		}
		TextView lblchilditem = (TextView) convertView
				.findViewById(R.id.lblchilditem);
		lblchilditem.setText(childText);

		return convertView;
	}

	@Override
	public int getChildrenCount(int parentPosition) {
		return this.listchild.get(this.listparent.get(parentPosition)).size();
	}

	@Override
	public Object getGroup(int parentPosition) {
		return this.listparent.get(parentPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listparent.size();
	}

	@Override
	public long getGroupId(int parentPosition) {
		return parentPosition;
	}

	@Override
	public View getGroupView(int parentPosition, boolean isExpandable,
			View convertView, ViewGroup parent) {

		String parenttitle = (String) getGroup(parentPosition);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expndable_parent, null);
		}
		TextView lbl_parent = (TextView) convertView
				.findViewById(R.id.lbl_parent);
		lbl_parent.setTypeface(null, Typeface.BOLD);
		lbl_parent.setText(parenttitle);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int parentPosition, int childPosition) {

		return true;
	}

}
