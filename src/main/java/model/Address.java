package model;

public class Address {
	
	private String street;
	private String streetnumber;
	
	
	public Address(String street, String streetnumber) {
		this.street = street;
		this.streetnumber = streetnumber;
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
	
	
}
