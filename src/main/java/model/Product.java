package model;

public class Product{
	// TODO: Hoe kan je laten zien wat in de aanbieding is? Before en After prijs? Misschien fixen door nieuwe klasse "Aanbieding"
	private String name;
	private int id;
	private double price;
	private String description;
	private Categorie categorie;


	public Product(int newId, String newName, double newPrice, String newDescription) {
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

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof Product) {
			Product anderProduct = (Product) anderObject;

			if (this.id == anderProduct.getId() &&
					this.name.equals(anderProduct.name) &&
					this.price == anderProduct.getPrice() &&
					this.description.equals(anderProduct.getDescription())) {
				isEqual = true;
			}
		}
		return isEqual;
	}

}
