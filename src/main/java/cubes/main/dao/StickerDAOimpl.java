package cubes.main.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 

import cubes.main.entity.Sticker;

@Component
public class StickerDAOimpl implements StickerDAO{

	@Autowired // znaci da ce Spring da vodi racuna o ovom pojlu
	private SessionFactory sessionFactory;
	// za vezu sa bazom. Konfigurisan je u konfiguracionom fajlu
						// tu je definisan <bean session factory
	
	
	@Transactional
	@Override
	public List<Sticker> getStickerList() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		List<Sticker> list =  session.createQuery("from Sticker", Sticker.class).getResultList();
		return list;
	}

	@Transactional
	@Override
	public void saveSticker(Sticker s) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(s); // jer koristimo istu ovu metodu i za save i za update
		
	}
	
	
	

	@Transactional
	@Override
	public Sticker getStickerById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Sticker s = session.get(Sticker.class, id);
		return s;
	}
	

	@Transactional
	@Override
	public void deleteSticker(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Sticker where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
