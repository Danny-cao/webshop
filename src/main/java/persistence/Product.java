package persistence;

import java.util.List;

public class Product{
	private String name;
	private int id;
	private int price;
	private String beschrijving;
	
	
	public Product(int newId, String newName, int newPrice, String beschrijving) {
		id = newId;
		name = newName;
		price = newPrice;
		this.beschrijving = beschrijving;
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

	public String getBeschrijving() {
		return beschrijving;
	}

}
