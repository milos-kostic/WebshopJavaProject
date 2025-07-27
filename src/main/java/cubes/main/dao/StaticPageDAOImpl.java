package cubes.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.StaticPage;

@Repository
public class StaticPageDAOImpl implements StaticPageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	// treba nam Samo Sadrzaj te stranice, content.
	
	@Transactional
	@Override
	public String getAboutUsPageContent() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		  
		StaticPage aboutUsPage = session.get(StaticPage.class,1); // 1 - AboutUs page
		
		return aboutUsPage.getContent(); 
			// ova metoda vraca samo Content koji nam treba, a ne ceo objekat.
	}

	@Transactional
	@Override
	public String getLocationPageContent() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		  
		StaticPage locationPage = session.get(StaticPage.class,2); // 2 - Location page
		
		return locationPage.getContent(); 
			// ova metoda vraca samo Content koji nam treba, a ne ceo objekat.
	}

	@Transactional
	@Override
	public StaticPage getStaticPage(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(StaticPage.class, id);
	}

	@Transactional
	@Override
	public void saveStaticPage(StaticPage staticPage) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(staticPage);
	}

}
