package cubes.main.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Slider;



@Repository
public class SliderDAOImpl implements SliderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Slider> getSliderList() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Slider> list =  session.createQuery("from Slider", Slider.class).getResultList();
		return list;
	}

	@Transactional
	@Override
	public void saveSlider(Slider s) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(s); // jer koristimo istu ovu metodu i za save i za update
		
		
	}

	@Transactional
	@Override
	public Slider getSliderById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Slider s = session.get(Slider.class, id);
		return s;
	}

	@Transactional
	@Override
	public void deleteSlider(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		// Slider s = session.get(Slider.class, id); // ovde je lose sto se vrse dva upita na bazu
		// session.delete(s); // i get i delete rade upit.
		// zato pisemo upit koji ce samo jednom da radi na bazi:
		

		Query query = session.createQuery("delete from Slider where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	
	
	
}
