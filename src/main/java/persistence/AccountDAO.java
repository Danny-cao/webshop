package persistence;

import model.Account;

public interface AccountDAO {
	boolean validateLogin(String email, String password);
	Account getAccountDetails(String email);
}
