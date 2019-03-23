package persistence;

import java.util.List;

public class Product{
	private String name;
	private int id;
	private int price;
	
	
	public Product(int newId, String newName, int newPrice) {
		id = newId;
		name = newName;
		price = newPrice;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}

}
