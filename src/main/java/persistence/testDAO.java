package persistence;


import model.Product;

public class testDAO {
	public static void main(String[] args){
		AccountDAO accDAO = new AccountPostgreSQLDAOImpl();
		System.out.println(accDAO.validateLogin("test@test.nl", "test"));
		System.out.println(accDAO.validateLogin("test@tesst.nl", "test"));
		System.out.println(accDAO.validateLogin("test@test.nl", "tes2t"));
	}

}
