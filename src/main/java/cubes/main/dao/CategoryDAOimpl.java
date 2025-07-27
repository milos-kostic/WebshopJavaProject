package cubes.main.dao;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import cubes.main.entity.Category;

// @Component
@Repository
public class CategoryDAOimpl implements CategoryDAO{

	// za vezu sa bazom:
	@Autowired // ti napravi ovo polje, znaci nigde ne pisem: ... = new SessionFactory
	private SessionFactory sessionFactory;
			// Singleton, samo jedan objekat napravi za ceo projekat
	
	
	
	// obavezna metoda interfejsa:
	// @Transactional  // prebaceno u servis
	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		List<Category> categories = session.createQuery("from Category",Category.class).getResultList();
		
		return categories;
	}


	 // ovde ne ide @Transactional
	@Override
	public List<Category> getCategoryListForMainPage() {
		Session session = sessionFactory.getCurrentSession();
		List<Category> list = session.createQuery("from Category c where c.homepage=1",Category.class).getResultList();
		return list;
	}

	
	

	// @Transactional
	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}



	//@Transactional
	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Category cat = session.get(Category.class,id);
		return cat;
	}



	//@Transactional
	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		
		 Session session = sessionFactory.getCurrentSession();
		 
		// 1. nacin:  	
		// Category cat = session.get(Category.class, id);
		// session.delete(cat);
		
		 
		// ali radi dva upita: 1. dohvati, 2. obrisi. Radimo na drugi nacin:
		// 2.
		Query query = session.createQuery("delete from Category where id=:id"); // ...= :id" // sql u hibernate
		// Query query = session.createQuery("delete from Category where id=:id and price< :p"); // ...= :id" // sql u hibernate
				// Query iz paketa: import org.hibernate.query
		// query.setParameter("p", 120);
		query.setParameter("id", id);
		query.executeUpdate();		
		
	}



	
	
	
	
}
