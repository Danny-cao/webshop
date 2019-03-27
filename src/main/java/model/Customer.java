package model;

public class Customer {
	private int id;
	private String name;
	private Address address;
	private String image;

	public Customer(String name, Address address, String image) {
		this.name = name;
		this.address = address;
		this.image = image;
	}

	public Customer(int id, String name, Address address, String image) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof Customer) {
			Customer anderCustomer = (Customer) anderObject;

			if (this.id == anderCustomer.getId() &&
					this.name.equals(anderCustomer.getName()) &&
					this.image.equals(anderCustomer.getImage()) &&
					this.address.equals(anderCustomer.getAddress())) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
