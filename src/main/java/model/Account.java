package model;

import java.sql.Date;
import java.util.Objects;

public class Account {
	private int id;
    private Customer customer;
    private String email;
    private String password;

    private String facturingAddress;
    private boolean isActive;
    private Date opendate;

    public Account(Customer customer, String email, String password, String facturingAddress, boolean isActive, Date opendate) {
        this.customer = customer;
        this.email = email;
        this.password = password;
        this.facturingAddress = facturingAddress;
        this.isActive = isActive;
        this.opendate = opendate;
    }

    public Account(int id, Customer customer, String email, String password, String facturingAddress, boolean isActive, Date opendate) {
		this.id = id;
        this.customer = customer;
        this.email = email;
        this.password = password;
        this.facturingAddress = facturingAddress;
        this.isActive = isActive;
        this.opendate = opendate;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacturingAddress() {
        return facturingAddress;
    }

    public void setFacturingAddress(String facturingAddress) {
        this.facturingAddress = facturingAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                isActive() == account.isActive() &&
                Objects.equals(getCustomer(), account.getCustomer()) &&
                Objects.equals(getEmail(), account.getEmail()) &&
                Objects.equals(getPassword(), account.getPassword()) &&
                Objects.equals(getFacturingAddress(), account.getFacturingAddress()) &&
                Objects.equals(getOpendate(), account.getOpendate());
    }
}
