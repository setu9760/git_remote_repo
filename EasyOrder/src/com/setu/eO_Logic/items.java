package com.setu.eO_Logic;

public class items {

	private long id;
	private String item;

	public long getid() {
		return id;
	}

	public void setid(long id) {
		this.id = id;
	}

	public String getitem() {
		return item;
	}

	public void setitem(String item) {
		this.item = item;
	}
	@Override
	public String toString(){
		return item;
	}
}
