package cubes.main.service;

import java.util.List;

import cubes.main.entity.Category;

public interface CategoryService {

	// treba da ima sve isto sto i CategoryDAO, kopiramo sve metode
	
	 public List<Category> getCategoryList(); // Category je nova klasa model.	 
		// interfejs je, samo potpis metode ne i def

	 public void saveCategory(Category category);

	 public Category getCategoryById(int id);

	 public void deleteCategory(int id); // moze i cela kateg a moze i id
	 
	 public  List<Category> getCategoryListForMainPage();
}
