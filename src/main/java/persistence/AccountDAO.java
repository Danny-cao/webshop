package persistence;

public interface AccountDAO {
	boolean validateLogin(String email, String password);
}
