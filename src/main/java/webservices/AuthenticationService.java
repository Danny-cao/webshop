package webservices;

import model.Account;
import persistence.AccountDAO;
import persistence.AccountPostgreSQLDAOImpl;

public class AuthenticationService {
	AccountDAO accDAO = new AccountPostgreSQLDAOImpl();
	public Account getAccount(String email) {
		return accDAO.getAccountDetails(email);
	}
}
