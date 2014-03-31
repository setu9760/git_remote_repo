package com.setu.eO_Logic;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

	/**
	 * @param context
	 */
	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_TABLE1_SELECTION);
			db.execSQL(CREATE_TABLE2_PIZZA);
		} catch (SQLException e) {
			Log.e("Setu", "sql error: " + e);
		}
		Log.i("Setu", "database ceated");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			Log.w("Setu", "Upgrading form version " + oldVersion + "to "
					+ newVersion + "\n all data destroyed");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELECTION);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIZZA);
			onCreate(db);
		}

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
		if (oldVersion > newVersion) {
			Log.w("Setu", "downgrading the database from version " + newVersion
					+ "to" + oldVersion);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELECTION);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIZZA);
			onCreate(db);
		}
	}

	/**
	 * @param selection
	 */
	public void addselection(Selection selection) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_ID, selection.get_id());
			values.put(KEY_ITEMS, selection.get_item());
			db.insert(TABLE_SELECTION, null, values);
			db.close();
			Log.i("Setu", " row added in main ");

		} catch (SQLException e) {
			Log.e("Setu", "sql error: " + e);
		} catch (Exception e) {
			Log.e("Setu", "other error: " + e);
		}
	}

	public void add_all_products(All_products all_products) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_ID, all_products.get_id());
			values.put(KEY_PRODUCT_CATAGORY, all_products.get_catagory());
			values.put(KEY_ITEMS, all_products.get_item());
			values.put(KEY_PRICE, all_products.get_price());
			values.put(KEY_VEG, all_products.get_veg());
			db.insert(TABLE_PIZZA, null, values);
			db.close();
			Log.i("Setu", " row added in pizza ");
		} catch (SQLException e) {
			Log.e("Setu", "sql error: " + e);
		}
	}

	/**
	 * @return
	 */
	public List<Selection> getallselection() {
		List<Selection> selectionlist = new ArrayList<Selection>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(ALL_SELECTION_QUERY, null);
		/*
		 * Looping through all the rows of the table and passing row as
		 * selection object and adding it to the selectionlist list.
		 */
		if (cursor.moveToFirst()) {
			do {
				Selection selection = new Selection();
				selection.set_id(Integer.parseInt(cursor.getString(0)));
				selection.set_item(cursor.getString(1));
				selectionlist.add(selection);
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
		return selectionlist;
	}

	/*
	 * following method is for development stage only and is not to be used or
	 * kept in the code as it allows direct access to the product table which is
	 * unnecessary for this application
	 */
	public List<All_products> get_all_products_list() {
		List<All_products> product_list = new ArrayList<All_products>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(ALL_PRODUCT_QUERY, null);
		/*
		 * Looping through all the rows of the table and passing row as
		 * selection object and adding it to the selectionlist list.
		 */
		if (cursor.moveToFirst()) {
			do {
				All_products all_products = new All_products();
				all_products.set_id(Integer.parseInt(cursor.getString(0)));
				all_products
						.set_catagory(Integer.parseInt(cursor.getString(1)));
				all_products.set_item(cursor.getString(2));
				all_products.set_price(Float.parseFloat(cursor.getString(3)));
				all_products.set_veg(Integer.parseInt(cursor.getString(4)));
				product_list.add(all_products);
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
		return product_list;
	}

	// //////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////

	public List<All_products> get_one_catagory_product(int p_catagory) {
		List<All_products> products_list = new ArrayList<All_products>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery(CATAGORY_QUERY + p_catagory, null);
		if (cursor.moveToFirst()) {
			do {
				All_products all_products = new All_products();
				all_products.set_id(Integer.parseInt(cursor.getString(0)));
				all_products
						.set_catagory((Integer.parseInt(cursor.getString(1))));
				all_products.set_item(cursor.getString(2));
				all_products.set_price(Float.parseFloat(cursor.getString(3)));
				all_products.set_veg(Integer.parseInt(cursor.getString(4)));
				products_list.add(all_products);
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
		return products_list;
	}

	/**
	 * @return
	 */
	public int rowcount() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(COUNT_ALL_QUERY, null);
		int temp_row_count = cursor.getCount();
		db.close();
		cursor.close();
		return temp_row_count;
	}

	/*
	 * PRIVATE VARIABLE DECLARATION / ********DO NOT MODIFY*******
	 */
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "EasyOrder";
	private static final String TABLE_SELECTION = "selection";
	private static final String TABLE_PIZZA = "pizza";
	private static final String KEY_ID = "_id";
	private static final String KEY_ITEMS = "_items";
	private static final String KEY_PRICE = "_price";
	private static final String KEY_PRODUCT_CATAGORY = "_catagory";
	private static final String KEY_VEG = "_veg";

	private static final String CREATE_TABLE1_SELECTION = "CREATE TABLE  "
			+ TABLE_SELECTION + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_ITEMS + " TEXT NOT NULL " + " ) ";

	private static final String CREATE_TABLE2_PIZZA = "CREATE TABLE "
			+ TABLE_PIZZA + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_PRODUCT_CATAGORY + " INTEGER NOT NULL, " + KEY_ITEMS
			+ " TEXT NOT NULL, " + KEY_PRICE + " REAL NOT NULL, " + KEY_VEG
			+ " INTEGER NOT NULL " + " ) ";

	private static final String ALL_SELECTION_QUERY = "SELECT * FROM "
			+ TABLE_SELECTION;

	private static final String ALL_PRODUCT_QUERY = "SELECT * FROM "
			+ TABLE_PIZZA;

	private static final String COUNT_ALL_QUERY = "SELECT * FROM "
			+ TABLE_SELECTION;

	private static final String CATAGORY_QUERY = " SELECT * FROM "
			+ TABLE_PIZZA + " WHERE " + KEY_PRODUCT_CATAGORY + " = ";

	// ******* END OF PRIVATE VARIABLE DEVLARATION ***********

}
