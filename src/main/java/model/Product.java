package model;

public class Product{
	// TODO: Hoe kan je laten zien wat in de aanbieding is? Before en After prijs? Misschien fixen door nieuwe klasse "Aanbieding"
	private String name;
	private int id;
	private double price;
    private String picture;
	private String description;
	private Category category;


    public Product(String newName, double newPrice, String picture, String newDescription) {
		this.name = newName;
		this.price = newPrice;
        this.picture = picture;
		this.description = newDescription;
	}

    public Product(int id, String newName, double newPrice, String picture, String newDescription) {
        this.id = id;
        this.name = newName;
        this.price = newPrice;
        this.picture = picture;
        this.description = newDescription;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

	public Category getCategory() {
		return category;
	}

    public void setId(int id) {
        this.id = id;
    }

	public void setName(String name) {
		this.name = name;
	}

    public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(Category category) {
		this.category = category;
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
