package model;

import java.util.Objects;

public class Account {

	private int id;
	private String email;
	private String password;
	private String adress;


	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Account(String email, String password) {

		this.email = email;
		this.password = password;
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

	public Account(int id) {
		this.id = id;
	}

	public Account(int idNew, String emailNew, String adressNew) {
		this.id = idNew;
		this.email = emailNew;
		this.adress = adressNew;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                Objects.equals(getEmail(), account.getEmail()) &&
                Objects.equals(getPassword(), account.getPassword());
    }
}
