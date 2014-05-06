package com.setu.eO_Logic;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.setu.eO_GUI.Splash_activity;

public class JSONParser {

	private final String JSON_STRING;

	public JSONParser(String jsonstring) {

		this.JSON_STRING = jsonstring;

	}

	public String getJSON_STRING() {
		return JSON_STRING;
	}

	public void parse_selection() throws JSONException {
		JSONObject jsonobject = new JSONObject(getJSON_STRING());
		JSONArray jsonarray = jsonobject.getJSONArray("selection");
		Splash_activity.sel_id.clear();
		Splash_activity.sel_list.clear();
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject objects = jsonarray.getJSONObject(i);
			Splash_activity.sel_id.add(objects.getInt("id"));
			Splash_activity.sel_list.add(objects.getString("items"));
		}
	}

	public void parse_all_products() throws JSONException {
		JSONObject jsonobject = new JSONObject(getJSON_STRING());
		JSONArray jsonarray = jsonobject.getJSONArray("all_products");
		Splash_activity.p_id.clear();
		Splash_activity.p_cat_id.clear();
		Splash_activity.p_name.clear();
		Splash_activity.p_price.clear();
		Splash_activity.p_veg.clear();
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject objects = jsonarray.getJSONObject(i);
			Splash_activity.p_id.add(objects.getInt("id"));
			Splash_activity.p_cat_id.add(objects.getInt("catagory"));
			Splash_activity.p_name.add(objects.getString("items"));
			Splash_activity.p_price.add(objects.getDouble("price"));
			Splash_activity.p_veg.add(objects.getInt("veg"));
		}
	}
}
