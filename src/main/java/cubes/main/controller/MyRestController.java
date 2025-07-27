package cubes.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.ProductDAO;
import cubes.main.dao.StickerDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Product;
import cubes.main.entity.Sticker;
import cubes.main.rest.CategoryException;
import cubes.main.rest.MessageResponse;
import cubes.main.rest.request_body.ProductFilter;
import cubes.main.rest.response.BasicProductResponse;
import cubes.main.rest.response.SettingResponse;
import cubes.main.service.CategoryService;

@RestController
@RequestMapping(value="/api")
public class MyRestController {

	@Autowired
	// private CategoryDAO categoryDAO;  kategorija je servis:
	private CategoryService categoryService;

	@Autowired
	private StickerDAO stickerDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/test")
	public String testMethod() {
		
		return "Cubes";
	}
	
	
	// ------------------  CATEGORIES  ---------------------------
	
	// @RequestMapping(value="/categories")   moze i ovako ali treba:
	@GetMapping(value="/categories")
	public List<Category> getCategories(){	
		// List<Category> list = categoryService.getCategoryList();		
		// lista kateogorija treba da se salje u Json formatu.
		
		return categoryService.getCategoryList(); // list;
	}
	
	
	@GetMapping(value="/categories/{id}")
	public Category getCategoryById(@PathVariable int id) {
		// System.out.println("id: "+id);	
		
		// ako vrati null nije greska zato napravimo da izbaci gresku pa da ispisemo:
		Category cat = categoryService.getCategoryById(id);  //ova metoda je iz servisa Nije ista ova u kojoj smo
		
		if(cat==null) {
			throw new CategoryException("Kategorija sa id: "+id+" ne postoji u bazi podataka."); // RuntimeException();
		}
		
		// ako ne vracam gresku ovoaj red je dovoljan:
		return  categoryService.getCategoryById(id);  // category;
	}
	
	
	// cesto pitanje:
	// razlika Post I Put  (u RestApi kontroleru): post: kao create a put: kao update
	@PostMapping(value="/categories")   
	// @RequestBody: u zahjtevu saljem I Body a u body je objekat (npr kategorija koju cu da cuvam):
		//		kateg u bodiju moze da bude u Json formatu
	// public Category createCategory(@RequestBody Category category) { // putCategory() {
	public MessageResponse createCategory(@RequestBody Category category) { // putCategory() {
		categoryService.saveCategory(category);   
		// da ako slucajno posalje id - ne upise u bazu cime bi preklopio postojecu kateg:
		category.setId(0);
		return new MessageResponse(
				HttpStatus.OK.value(),
				"Uspesno je sacuvana kategorija: "+category,
				System.currentTimeMillis()
				); // code, message, time. Status.ok moze i 200.
	} // saljem httpPequest na server (tip post metode), u requestu je kategorija, 
		// server prihvata tu kateg u ovoj metodi, 
		// smesta je u bazu koristeci hibernate metodu saveCategory, 
		// kao odgovor u response vraca info o toj kateg koja je poslata i sacuvana.
	
	
	
	
	
	
	// isto za update samo Put:
	//	razlika createCategory I updateCategory je:
	//		createCat je @PostMapping, radi insert,
	//		a updateCat je @PutMapping radi update
	@PutMapping(value="/categories")
	// public Category updateCategory(@RequestBody Category category) { 
	public MessageResponse updateCategory(@RequestBody Category category) {
				// ovde treba da se postavi id kateg Koju menjam
		
		// ako trazi klijent i da napisem stanje pre izmene: sacuvam obj pre izmene: npr:
		Category oldCat = categoryService.getCategoryById(category.getId()); // pa moze da se ispise
	
		categoryService.saveCategory(category);
		
		return new MessageResponse(
				200,
				"Uspesno je izmenjena kategorija: "+category,
				System.currentTimeMillis()
				); // code, message, time. Status.ok moze i 200.
	}
	
	
	
	
	
	@DeleteMapping(value="/categories/{id}") // {id} je path putanja za id
	// public Category deleteCategory(@PathVariable int id) {
	public MessageResponse deleteCategory(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		categoryService.deleteCategory(id);
			// moze: uspesno sam izbrisao, evo ti json kategorije koja je obrisana. 
			// i klijent prikaze tu obrisanu kateg iz json-a koji je vracen 
			// ako hoce undo onda uradi Create te obrisane kateg
		return new MessageResponse(HttpStatus.OK.value(),	
					"Uspesno je izbrisana kategorija: " + category,
					System.currentTimeMillis()
					);
	}
	
	
	

	
	// ------------------  STICKERS  ---------------------------
 
	@GetMapping(value="/stickers")
	public List<Sticker> getStickers(){	
		List<Sticker> list = stickerDAO.getStickerList();		
		// lista kateogorija treba da se salje u Json formatu.
		
		return   list; // categoryService.getCategoryList(); // list;
	}
	
	 
	@GetMapping(value="/stickers/{id}")
	public Sticker getStickerById(@PathVariable int id) {
			// treba jos uzeti stiker da li je null pa baciti izuzetak kao za kateg, sad ostavljam ovako
		return stickerDAO.getStickerById(id);
	}
	
	@PostMapping(value="/stickers")
	public MessageResponse saveSticker(@RequestBody Sticker sticker) {
		sticker.setId(0); // za svaki slucaj
		stickerDAO.saveSticker(sticker);
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste sacuvali stiker: "+ sticker,
				System.currentTimeMillis()
				);
				
	}
	
	
	@PutMapping(value="/stickers")
	public MessageResponse updateSticker(@RequestBody Sticker sticker) { 
		stickerDAO.saveSticker(sticker);
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste izmenili stiker: "+ sticker,
				System.currentTimeMillis()
				);
				
	}
	
	@DeleteMapping(value="/stickers/{id}")
	public MessageResponse deleteSticker(@PathVariable int id) { 
		Sticker sticker = stickerDAO.getStickerById(id);
		stickerDAO.deleteSticker(id);
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste obrisali stiker: "+ sticker,
				System.currentTimeMillis()
				);
				
	}
	
	
	// ------------------------------- SETTINGS ---------------------------- 
	//  ZA grupisanje iz vise entiteta u jedan odgovor kad ima potrebe:
	@GetMapping(value="/settings")
	public  SettingResponse getSettings() {
		SettingResponse settings = new SettingResponse();
		
		// popunjavam potrebnim podacima:
		settings.setCategories(categoryService.getCategoryList());
		settings.setStickers(stickerDAO.getStickerList());
		
		return  settings;
	}
	
	
	
	// ------------------------------ PRODUCTS ------------------------------
	// prvo servis da vrati sve proizvode:
	
	@GetMapping (value="/products")
	public List<Product> getProducts(){
		// List<Product> products = productDAO.getProductList();
		List<Product> products = productDAO.getProductListWithStickers();
		return products;
		
	}
	
	// kad treba da vrati ne sva polja iz proizvoda ali za Sve proizvode:
	@GetMapping(value="/basicproducts")
	public List<BasicProductResponse> getBasicProducts(){
		
		List<BasicProductResponse> list = new ArrayList<BasicProductResponse>();
		List<Product> products = productDAO.getProductListWithStickers(); // prava lista proizvoda
		
		for(Product p:products) {
			list.add(new BasicProductResponse(p));
		}
		
		return list;
	}
	
	
	
	// post  metod da sacuvam proizvod:
	@PostMapping(value="/products")
	public MessageResponse saveProduct(@RequestBody Product product) {
		
		product.setId(0); // za svaki slucaj
		productDAO.saveProduct(product);
		
		return new MessageResponse(
					HttpStatus.OK.value(),
					"Uspesno ste sacuvali proizvod: "+product,
					System.currentTimeMillis()
				); // code, message, time
		
	}
	
	
		// put  metod za izmenu reda u bazi:
		@PutMapping(value="/products")
		public MessageResponse updateProduct(@RequestBody Product product) {
			 
			productDAO.saveProduct(product);
			
			return new MessageResponse(
						HttpStatus.OK.value(),
						"Uspesno ste izmenili proizvod: "+product,
						System.currentTimeMillis()
					); // code, message, time
			
		}
		
	
		// delete metod:
		@DeleteMapping(value="/products/{id}")
		public MessageResponse deleteProduct(@PathVariable int id) {
			 
			Product p= productDAO.getProductById(id);
				
			productDAO.deleteProduct(id);
			
			return new MessageResponse(
						HttpStatus.OK.value(),
						"Uspesno ste obrisali proizvod: "+p,
						System.currentTimeMillis()
					); // code, message, time
			
		}
	
		
		// pretraga po kateg, ceni i stikerima kao sto je metoda u ProductDAO: 
		//   public List<Product> getProductList(Integer category, Integer price, Integer[] stickers) {
		@GetMapping(value="/filter-products") // nova ruta
		public List<Product> getProductListFilter(@RequestBody ProductFilter filter){ // nova klasa u paketu /request_body
			
			List<Product> list = productDAO.getProductList(filter.getCategory(), filter.getPrice(), filter.getStickers());
			
			return list;
		}
		
	
	
}
