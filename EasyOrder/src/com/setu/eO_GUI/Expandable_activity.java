package com.setu.eO_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.setu.EasyOrder.R;
import com.setu.eO_Adapters.Expandableadapter;
import com.setu.eO_Logic.All_products;
import com.setu.eO_Logic.DBhelper;
import com.setu.eO_Logic.InitDB;
import com.setu.eO_Logic.Selection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class Expandable_activity extends Activity implements
		OnChildClickListener, OnGroupClickListener, OnGroupCollapseListener,
		OnGroupExpandListener {

	Expandableadapter adapter;
	ExpandableListView expview;
	List<String> listparent;
	HashMap<String, List<String>> hashmap;
	DBhelper db;

	List<Selection> parentselectionlist;
	List<String> child = new ArrayList<String>();

	List<All_products> allchildlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_main);

		db = new DBhelper(this);
		@SuppressWarnings("unused")
		final InitDB init = new InitDB(this);
		parentselectionlist = db.getallselection();

		allchildlist = db.get_all_products_list();

		expview = (ExpandableListView) findViewById(R.id.explayout);

		prepareListData();

		adapter = new Expandableadapter(this, listparent, hashmap);

		expview.setAdapter(adapter);
	}

	/**
	 * 
	 */
	public void prepareListData() {
		listparent = new ArrayList<String>();
		hashmap = new HashMap<String, List<String>>();
		List<String> tempchild = new ArrayList<String>();

		tempchild.add("abcd");
		tempchild.add("lasfk");
		tempchild.add("sdofkdf");

		Log.i("Setu", "size of parent selection: " + parentselectionlist.size());
		Log.i("Setu", "size of all child selection: " + allchildlist.size());

		for (Selection selection : parentselectionlist) {
			listparent.add(selection.get_item());
			hashmap.put(selection.get_item(), tempchild);
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		return false;
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		Toast.makeText(this, "clicked on " + ++groupPosition, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onGroupCollapse(int groupPosition) {

	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		return false;
	}
}
