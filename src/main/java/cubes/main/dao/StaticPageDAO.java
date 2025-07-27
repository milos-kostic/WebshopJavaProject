package cubes.main.dao;

import cubes.main.entity.StaticPage;

public interface StaticPageDAO {

	public String getAboutUsPageContent();
	
	public String getLocationPageContent();
	
	// za izmenu treba ceo objekat:
	public StaticPage getStaticPage(int id);
	public void saveStaticPage(StaticPage staticPage);
	
}
