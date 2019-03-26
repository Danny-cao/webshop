package persistence;

import model.Categorie;

public interface CategorieDAO {
	
	Categorie findByName(String name);
}
