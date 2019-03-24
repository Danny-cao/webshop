package persistence;


public class testDAO {
	public static void main(String[] args){
		ProductOracleBaseDAOImpl prodDAO = new ProductOracleBaseDAOImpl();

		for (Product p : prodDAO.findAllOnSale()) {
			System.out.println(p.getName() + p.getPrice());
		}
	}

}
