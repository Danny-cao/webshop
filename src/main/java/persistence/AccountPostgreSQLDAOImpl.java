package persistence;

public class AccountPostgreSQLDAOImpl extends PostgreSQLBaseDao implements AccountDAO{

	@Override
	public boolean validateLogin(String email, String password) {
		
		return false;
	}

}
