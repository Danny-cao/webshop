package model;

import java.util.ArrayList;

public class Order {
	
	private int id;
	private Account account;
	private Address address;
	
	
	public Order(int id, Account account, Address address) {
		this.id = id;
		this.account = account;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
