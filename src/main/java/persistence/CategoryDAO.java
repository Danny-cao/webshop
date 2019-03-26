package persistence;

import model.Category;

public interface CategoryDAO {
	
	Category findByName(String name);
}
