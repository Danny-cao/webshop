package model;

import java.sql.Date;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sale sale = (Sale) o;
		return Float.compare(sale.getPrice(), getPrice()) == 0 &&
				Objects.equals(getProduct(), sale.getProduct()) &&
				Objects.equals(getBegin(), sale.getBegin()) &&
				Objects.equals(getEnd(), sale.getEnd());
	}
}
