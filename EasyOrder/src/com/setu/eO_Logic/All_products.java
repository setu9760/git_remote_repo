package com.setu.eO_Logic;

public class All_products {
	int _id;
	int _catagory;
	String _item;
	double _price;
	int _veg;

	All_products() {

	}

	public All_products(int _id, int _catagory, String _item, double _price, int _veg) {
		this._id = _id;
		this._catagory = _catagory;
		this._item = _item;
		this._price = _price;
		this._veg = _veg;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_catagory() {
		return _catagory;
	}

	public void set_catagory(int _catagory) {
		this._catagory = _catagory;
	}

	public String get_item() {
		return _item;
	}

	public void set_item(String _item) {
		this._item = _item;
	}

	public double get_price() {
		return _price;
	}

	public void set_price(double _price) {
		this._price = _price;
	}

	public int get_veg() {
		return _veg;
	}

	public void set_veg(int _veg) {
		this._veg = _veg;
	}
}
