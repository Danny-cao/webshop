package persistence;

import java.util.List;

public class Product{
	private String name;
	private int id;
	private int price;
	private String description;
	
	
	public Product(int newId, String newName, int newPrice, String newDescription) {
		this.id = newId;
		this.name = newName;
		this.price = newPrice;
		this.description = newDescription;
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

	public String getDescription() {
		return description;
	}

}
