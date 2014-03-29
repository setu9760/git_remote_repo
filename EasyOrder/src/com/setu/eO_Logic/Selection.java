package com.setu.eO_Logic;

public class Selection {
	int _id;
	String _item;

	public Selection() {

	}
	public Selection(String _item){
		this._item = _item;
	}
	public Selection(int _id, String _item) {
		this._id = _id;
		this._item = _item;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_item() {
		return _item;
	}

	public void set_item(String _item) {
		this._item = _item;
	}
	
}
