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

		/*
		 * Five main selection products.
		 */
		db.addselection(new Selection(1, "Pizza"));
		db.addselection(new Selection(2, "Sandwich"));
		db.addselection(new Selection(3, "Desserts"));
		db.addselection(new Selection(4, "Drinks"));
		db.addselection(new Selection(5, "Meal-Deal"));
		/*
		 * Pizza list
		 */
		db.add_all_products(new All_products(1, 1, "Marghrita", 12.00, 0));
		db.add_all_products(new All_products(2, 1, "Plain chicken", 12.00, 1));
		db.add_all_products(new All_products(3, 1, "Roasted veg", 15.00, 0));
		db.add_all_products(new All_products(4, 1, "Roasted chicken", 15.00, 1));
		db.add_all_products(new All_products(5, 1, "Meat feast", 20.00, 1));
		db.add_all_products(new All_products(6, 1, "BBQ chicken", 20.00, 1));
		db.add_all_products(new All_products(7, 1, "Pepperoni", 25.00, 1));
		db.add_all_products(new All_products(8, 1, "Chicago Supreme", 30.00, 1));

		/*
		 * Sandwich Selection
		 */

		db.add_all_products(new All_products(9, 2, "Cheese & Onion", 3.99, 0));
		db.add_all_products(new All_products(10, 2, "Cheese & Tomato", 3.99, 0));
		db.add_all_products(new All_products(11, 2, "Prawn Cocktail", 5.00, 1));
		db.add_all_products(new All_products(12, 2, "Tuna Sweetcorn", 5.00, 1));
		db.add_all_products(new All_products(13, 2, "Chicken & salad", 5.00, 1));
		db.add_all_products(new All_products(14, 2, "Meat and Cheese", 5.00, 1));

		/*
		 * Dessert Selection
		 */

		db.add_all_products(new All_products(15, 3, "Strawberry cake", 15.00,
				-1));
		db.add_all_products(new All_products(16, 3, "Blackforest Pudding",
				20.00, -1));
		db.add_all_products(new All_products(17, 3, "Lemon Cheescake", 15.00,
				-1));

		/*
		 * Drinks Selection
		 */

		db.add_all_products(new All_products(18, 4, "Coke 2l", 2.99, -1));
		db.add_all_products(new All_products(19, 4, "Diet Coke 2l", 2.99, -1));
		db.add_all_products(new All_products(20, 4, "Fanta 2l", 2.99, -1));
		db.add_all_products(new All_products(21, 4, "Sprite 2l", 2.99, -1));

		/*
		 * Meal Deal section
		 */

		db.add_all_products(new All_products(22, 5, "Family Meal for 4", 45.00,
				1));
	}
}
