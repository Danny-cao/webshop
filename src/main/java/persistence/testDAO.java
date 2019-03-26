package persistence;


import model.Product;

public class testDAO {
	public static void main(String[] args){
		ProductPostgreSQLDAOImpl prodDAO = new ProductPostgreSQLDAOImpl();

		for (Product p : prodDAO.findAllOnSale()) {
			System.out.println(p.getName() + p.getPrice());
		}
	}

}
