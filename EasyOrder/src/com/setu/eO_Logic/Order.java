package com.setu.eO_Logic;

/**
 * @author setu
 * 
 */

public class Order {

	private String product;
	private double price;

	public Order() {
	}

	public Order(String product, double price) {
		this.product = product;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
