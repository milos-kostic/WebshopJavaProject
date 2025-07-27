package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Product;

public interface ProductDAO {

	public List<Product> getProductList();  // ili getProducts. Vraca Sve proizvode
	
	public List<Product> getProductListWithStickers(); // da imam posebnu metodu
	
	public void saveProduct(Product product);
	
	public void deleteProduct(int id);
	
	public Product getProductById(int id); // vrati jedan proizvod
	
	public List<Product> getProductListForMainPage();
	
	public List<Product> getProductList(Integer category, Integer price, Integer[] stickers);
	
    public List<Product> getProductList(String text);
    
}
