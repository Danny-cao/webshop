package persistence;


public class testDAO {
	public static void main(String[] args){
		ProductOracleDAOImpl prodDAO = new ProductOracleDAOImpl();

		for (Product p : prodDAO.findAllOnSale()) {
			System.out.println(p.getName() + p.getPrice());
		}
	}

}
