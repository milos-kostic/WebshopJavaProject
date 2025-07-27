package cubes.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.dao.CategoryDAO;
import cubes.main.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	// kontroler koristi Service, a servis koristi DAO. znaci Servis ima DAO:
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Transactional // ovde dodajemo @Transactional, a sa DAO klasa je uklanjamo
	@Override
	public List<Category> getCategoryList() {
		// Ovde je kod koji ne treba da je ni u kontroleru niti u DAO klasi:
		
		
		return categoryDAO.getCategoryList(); // null;
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDAO.saveCategory(category);
	}

	@Transactional
	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryById(id);
	}

	@Transactional
	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		categoryDAO.deleteCategory(id);
	}

	@Transactional
	@Override
	public List<Category> getCategoryListForMainPage() {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryListForMainPage();
	}

	
	
	
}
