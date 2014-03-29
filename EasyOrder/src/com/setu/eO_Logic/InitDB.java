/**
 * 
 */
package com.setu.eO_Logic;

import android.content.Context;

/**
 * @author setu
 * 
 */
public final class InitDB {
	private final DBhelper db;

	/**
	 * @param context
	 */
	public InitDB(Context context) {
		db = new DBhelper(context);
		db.addselection(new Selection(1, "Pizza"));
		db.addselection(new Selection(2, "Sandwich"));
		db.addselection(new Selection(3, "Desserts"));
		db.addselection(new Selection(4, "Drinks"));
		db.addselection(new Selection(5, "Meal-Deal"));

		db.add_all_products(new All_products(1, 1, "Marghrita", 15.00, 0));
		db.add_all_products(new All_products(2, 1, "Plain chicken", 12.00, 1));
		db.add_all_products(new All_products(3, 1, "Roasted veg", 15.00, 0));
		db.add_all_products(new All_products(4, 1, "Roasted chicken", 15.00, 1));
		db.add_all_products(new All_products(5, 1, "Meat feast", 20.00, 1));
		db.add_all_products(new All_products(6, 1, "BBQ chicken", 20.00, 1));
		db.add_all_products(new All_products(7, 1, "Pepperoni", 25.00, 1));
		db.add_all_products(new All_products(8, 1, "Chicago Supreme", 30.00, 1));
		db.add_all_products(new All_products(9, 2, "Veg", 15.00, 1));
		db.add_all_products(new All_products(10, 2, "Veg", 45.00, 1));
		db.add_all_products(new All_products(11, 3, "Veg", 65.00, 3));
		/*
		 * db.add_all_products(new All_products(12, 3, "Veg", 35.00));
		 * db.add_all_products(new All_products(13, 3, "Veg", 15.00));
		 * db.add_all_products(new All_products(14, 3, "Veg", 45.00));
		 * db.add_all_products(new All_products(15, 3, "Veg", 65.00));
		 * db.add_all_products(new All_products(16, 4, "Veg", 35.00));
		 * db.add_all_products(new All_products(17, 4, "Veg", 25.00));
		 * db.add_all_products(new All_products(18, 4, "Veg", 15.00));
		 * db.add_all_products(new All_products(19, 4, "Veg", 45.00));
		 * db.add_all_products(new All_products(20, 4, "Veg", 65.00));
		 * db.add_all_products(new All_products(21, 5, "Veg", 65.00));
		 * db.add_all_products(new All_products(22, 5, "Veg", 65.00));
		 * db.add_all_products(new All_products(23, 5, "Veg", 65.00));
		 * db.add_all_products(new All_products(24, 5, "Veg", 65.00));
		 * db.add_all_products(new All_products(25, 5, "Veg", 65.00));
		 */
	}
}
