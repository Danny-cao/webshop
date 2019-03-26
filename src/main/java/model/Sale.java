package model;

import java.sql.Date;

public class Sale {
	
	private Product product;
	private Date begin;
	private Date end;
	private float price;
	
	public Sale(Product product, Date begin, Date end, float price) {
		this.product = product;
		this.begin = begin;
		this.end = end;
		this.price = price;
	}
	
	public Product getProduct() {
		return product;
	}
	public Date getBegin() {
		return begin;
	}
	public Date getEnd() {
		return end;
	}
	
	public float getPrice() {
		return price;
	}
}
