package cubes.main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.TransactionScoped;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;
import cubes.main.entity.Product;


@Repository
public class ProductDAOimpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Product> getProductList() {  // bez stikera
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("from Product",Product.class).getResultList();
		
		return list;
	}
	
	
	// posebna metoda za proizvode i posebna za proizvode Sa Stikerima:
	@Transactional 
	@Override
	public List<Product> getProductListWithStickers() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("from Product",Product.class).getResultList();
		
		// ucitavam i stikere:
		for(Product p : list) {
			Hibernate.initialize(p.getStickers());
		}
				
		return list;
	}
	
	
	

	@Transactional
	@Override
	public List<Product> getProductListForMainPage() {  // ovde trebaju i stikeri:
		Session session = sessionFactory.getCurrentSession();
		
		List<Product> list = session.createQuery("from Product p where p.homepage=1", Product.class).getResultList();
	 
		// ucitavam i stikere:
		for(Product p : list) {
			Hibernate.initialize(p.getStickers());
		}
	
		return list;
	}
	
	
	@Transactional
	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
	}

	@Transactional
	@Override
	public void deleteProduct(int id) { // kopiram iz stikera ili ketegorije:
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		 
			// 1. nacin:  	
			// Category cat = session.get(Category.class, id);
			// session.delete(cat);
			
			 
			// ali radi dva upita: 1. dohvati, 2. obrisi. Radimo na drugi nacin:
			// 2.
			Query query = session.createQuery("delete from Product where id=:id"); // ...= :id" // sql u hibernate
			// Query query = session.createQuery("delete from Category where id=:id and price< :p"); // ...= :id" // sql u hibernate
					// Query iz paketa: import org.hibernate.query
			// query.setParameter("p", 120);
			query.setParameter("id", id);
			query.executeUpdate();		
			
	}

	@Transactional
	@Override
	public Product getProductById(int id) { // kopiram iz stikera ili kategorije:
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);
		
		// trebaju i ovde stikeri: ucitavam i stikere:
		//for(Product product : list) {
			Hibernate.initialize(product.getStickers());
		//}
	
		return product;
	}

	
	// ima vise preklopljenih metoda sa ovim nazivom u ovoj klasi:
	@Transactional
	@Override
	// public List<Product> getProductList(int category) {
	public List<Product> getProductList(Integer category, Integer price, Integer[] stickers) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	 
		
		// 1. pravim upit: (formiram query String) ----------------------
		String queryString = "";
		if(stickers != null) {
			 queryString = "select distinct p from Product p left join p.stickers s ";
			 //				ubacujem join jer su stikeri u vezanoj tabeli
		}else {
			 queryString = "from Product p ";
			 
		}
		
		
		
		if(category!=null && category>0) {
			queryString = queryString + "where p.category.id = :categoryId ";
		}
		// Price:
		if(price!=null && price>0) {
			
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
			
		    queryString = queryString + " p.price < :price";
			 
		}
		// Stickers:  Stiker nije u entitetu Product nego je lista Stikera, join dve tabele
		if(stickers!=null && stickers.length>0) { // znaci dobio sam stikere u @RequestParametru
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
			
			// stikera moze biti vise, niz: join tabela
			queryString = queryString + " s.id IN (:stickers) ";

		    
		}
		
		System.out.println(queryString);
		
		
		
		// 2. postavljam parametre: (SETujem) ---------------------------
		Query query = session.createQuery(queryString);
		
		if(category!=null && category>0) {
			query.setParameter("categoryId",category);
		}
		if(price!=null && price>0) {
			query.setParameter("price", (double)(price*5000)); // price je 1, 2, 3 sifra cene
		}
		// stickers parametri, niz:
		if(stickers!=null && stickers.length>0) { 
			query.setParameterList("stickers", stickers); 
			//    Lista parametara, setParameterList
			// 		stickers je ceo niz Integer[] stickers parametar metode
		}
	  
		
		
		List<Product> products = query.getResultList();// ovde se izvrsava upit	
		
		// ovu metodu koristimo za filtriranje pa ne moraju stikeri.
		// ako hoces i stikere ovako se ucitavaju: 
		for(Product p : products) {
			Hibernate.initialize(p.getStickers());
		}
		
		
		return products;
		
	}

	
//	@Transactional
//	@Override
//	public List<Product> getProductList(String text) {
//		// TODO Auto-generated method stub
//		
//		Session session = sessionFactory.getCurrentSession();
//		
//		String stringQuery = "from Product p where p.title LIKE :text OR p.description LIKE :text"; 
//		// 													LIKE kad poredim Stringove
//		
//		Query query = session.createQuery(stringQuery);
//		
//		query.setParameter("text", "%"+text+"%");
//		 
//		
//		return query.getResultList();
//	}
	
	//   RADIMO ISTO OVO NA DRUGI NACIN:  
	
	//    ravnopravno se koriste oba nacina: 
	//   oslobadja nas SQL sintakse nego sve Java, koristi koju zelis: --------------
	@Transactional
	@Override
	public List<Product> getProductList(String text) {
	 
		Session session = sessionFactory.getCurrentSession();
		 
		Criteria criteria = session.createCriteria(Product.class); 
		//		parametar je klasa - entitet nad kojim primenjujemo pretragu 
		
		
		// dodajemo jos jedan uslov, ovo je Drugi tip: Criterion ne Criteria
		Criterion criteriaTitle = Restrictions.like("title", "%"+text+"%");
		Criterion criteriaDescription = Restrictions.like("description", "%"+text+"%");
		
		// kombinacija ova dva kriterijuma:
		LogicalExpression orExpression = Restrictions.or(criteriaTitle, criteriaDescription);
		
		// criteria.add(Restrictions.like("title", "%"+text+"%")); // koji je to kriterijum tj restrikcija
		criteria.add(orExpression);
		
		
		// ako na search stranici prikazejem stikere ovako ih dobavljam:
		// ovu metodu koristimo za filtriranje pa ne moraju stikeri.
				// ako hoces i stikere ovako se ucitavaju: 
		List<Product> products = criteria.list();
		
		for(Product p : products) {
			Hibernate.initialize(p.getStickers());
		}
				
				
				
		return products; // criteria.list(); ovako bi bila lista bez stikera
	}



	
}
