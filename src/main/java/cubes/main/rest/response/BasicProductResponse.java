package cubes.main.rest.response;

import cubes.main.entity.Product;

// klasa kad nam treba manje podataka: od pravog proizvoda izvude samo neka polja:
//  moze i poseban entitet u bazi ali radimo ovako
public class BasicProductResponse {

	
	// sta zelim da vratim u response kao json:
	private int id;
	private String title;
	private String description;
	private String category; // namerno string
	private String image;
	private double price;
	
	
	public BasicProductResponse() {
		
	}
	public BasicProductResponse(Product product) {
		this.id = product.getId();
		this.title=product.getTitle();
		this.description = product.getDescription();
		this.category = product.getCategory().getName(); // jer je string;
		this.image=product.getImage();
		this.price=product.getPrice();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
}
