package model;

public class Address {

	private int id;
	private String street;
	private String streetnumber;
	
	
	public Address(String street, String streetnumber) {
		this.street = street;
		this.streetnumber = streetnumber;
	}

	public Address(int id, String street, String streetnumber) {
		this.id = id;
		this.street = street;
		this.streetnumber = streetnumber;
	}

	public int getId() {
		return id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetnumber() {
		return streetnumber;
	}
	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof Address) {
			Address andereOrderRow = (Address) anderObject;

			if (this.id == andereOrderRow.getId() &&
					this.street.equals(andereOrderRow.getStreet()) &&
					this.streetnumber.equals(andereOrderRow.getStreetnumber())) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
