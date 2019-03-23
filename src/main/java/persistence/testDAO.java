package persistence;


public class testDAO {
	public static void main(String[] args){
		ProductOracleBaseDAOImpl prodDAO = new ProductOracleBaseDAOImpl();

		for (Product prod : prodDAO.findAll()) {
			System.out.println("Productname: " +prod.getName()+" ID: "+prod.getId()+ " Price: " + prod.getPrice() + " euro");
		}
	}

}
