package cubes.main.controller;

 
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.MessageDAO;
import cubes.main.dao.ProductDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.StaticPageDAO;
import cubes.main.dao.StickerDAO;
import cubes.main.dao.UserDAO;
import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Message;
import cubes.main.entity.Product;
import cubes.main.entity.Slider;
import cubes.main.entity.StaticPage;
import cubes.main.entity.Sticker;
import cubes.main.entity.User;
import cubes.main.service.CategoryService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired // da ga pravi Spring a ne rucno.
	// ne koristim vise u kontroeru DAO nego CategoryService, 
	//		a CategoryService koristi CategoryDAO
	// private CategoryDAO categoryDAO; 
	private CategoryService categoryService;
	
	@Autowired
	private StickerDAO stickerDAO;	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private SliderDAO sliderDAO;
	
	@Autowired
	private StaticPageDAO staticPageDAO;

	@Autowired
	private UserDAO userDAO;

	// ------------------------------------ dashboard ---------------------------------------
	@RequestMapping(value="/")
	public String getDashBoardPage(Model model) { // kasnije model
		
		long unreadMessagesCout= messageDAO.getUnreadMessagesCount();
		
		model.addAttribute("unreadMessagesCount",unreadMessagesCout);
		
		return "dashboard";
	}
	
	
	// ------------------------------------ CATEGORY ---------------------------------------
	
	@RequestMapping(value="/category-list")  // uvek ovako URL:  ...-...  a ne snakeCase
	public String getCategoryList(Model model) {  // da dobijemo model
		// return "category-list";  
			// kad traze listu kategorija - vrati pravu listu :
		
		// pravim listu:
		// List<Category> list = categoryDAO.getCategoryList(); // metoda koju smo napisali u CategioryDaoimpl valjda
		List<Category> list = categoryService.getCategoryList(); 
		
		model.addAttribute("categories",list); // podaci se salju na view str, tamo ce se zvati 'categories'
		
				System.out.println(list.toString()); // da vidim da li je vrario iz baze, pre nego posaljem na view stranicu
			
		return "category-list";
	}
	
	
//	@RequestMapping(value="/index")  // uvek ovako URL:  ...-...  a ne snakeCase
//	public String getIndex() {
//		return "index"; // pravimo stranicu .jsp koja se zove category-list
//	}
	
	
	
	
	// metoda za stranicu koja pravi novi red - kategoriju, i unosi u bazu:
	@RequestMapping(value="category-form")
	public String getCategoryForm(Model model) { // forma radi sa Objektom
		model.addAttribute("category",new Category()); // salji prazan objekat
		return "category-form";
		
	}
	
	
	// metoda za Cuvanje izmena, klikom na Sacuvaj kategoriju na formi:
	@RequestMapping(value="category-save")
	public String getCategorySave(@ModelAttribute Category category ) { 
									// objekat koji je dosao sa forme na prethodnoj str
		
		// categoryDAO.saveCategory(category);
		categoryService.saveCategory(category);
		
		return "redirect: category-list"; // preusmeri na str: category-list
				// ako je samo: return "category-list"; onda treba da imamo spremnu listu kateg, 
				// jer to trazi ova str
	}
	
	
	
	// za Update kategorije:
	@RequestMapping(value="category-update")
	public String getCategoryUpdate(@RequestParam int id, Model model) { // model - za postojecu kateg
		// Category  category = new Category(); // ne nova nego dohvatamo po id:
		// Category category = categoryDAO.getCategoryById(id);
		Category category = categoryService.getCategoryById(id);
		
		model.addAttribute("category",category);
		return "category-form";
	}
	
	
	@RequestMapping(value="category-delete")
	public String getCategoryDelete(@RequestParam int id) {
		
		// categoryDAO.deleteCategory(id);
		categoryService.deleteCategory(id);
		
		// kad izbrise, vraca se na istu stranicu.
		return "redirect: category-list";
	}
	
	
	
	// ------------------------------------ STICKER ---------------------------------------
	
	@RequestMapping(value="sticker-list")
	public String getStickerList(Model model) { 
		// da bi prosledio stranici listu sitkera treba da napravim model pa preko njega prenosim podatke 
		List<Sticker> list = stickerDAO.getStickerList();
		model.addAttribute("stickers",list);
						// pod ovim nazivom daljem podatke na stranicu.
		return "sticker-list"; // tako cemo je nazvati. Prosledjujemo joj listu stikera.
	}
	
	// metoda za stranicu koja pravi novi red - kategoriju, i unosi u bazu:
	@RequestMapping(value="sticker-form")
	public String getStickerForm(Model model) { // forma radi sa Objektom
		model.addAttribute("sticker",new Sticker()); // salji prazan objekat
		return "sticker-form";
		
	}
	
	 
	// metoda za Cuvanje izmena, klikom na Sacuvaj kategoriju na formi:
	@RequestMapping(value="sticker-save")
	public String getStickerSave(@ModelAttribute Sticker sticker ) { 
									// objekat koji je dosao sa forme na prethodnoj str
		
		stickerDAO.saveSticker(sticker);
		
		return "redirect: sticker-list"; // preusmeri na str: sticker-list
				// ako je samo: return "sticker-list"; onda treba da imamo spremnu listu sticker, 
				// jer to trazi ova str
	}
	
	
		// za Update stikera:
		@RequestMapping(value="sticker-update")
		public String getStickerUpdate(@RequestParam int id, Model model) { // model - za postojecu kateg
			// Sticker  sticker = new Sticker(); // ne nova nego dohvatamo po id:
			Sticker sticker = stickerDAO.getStickerById(id);
			
			model.addAttribute("sticker",sticker);
			return "sticker-form";
		}
		
		
		@RequestMapping(value="sticker-delete")
		public String getStickerDelete(@RequestParam int id) {
			
			stickerDAO.deleteSticker(id);
			
			// kad izbrise, vraca se na istu stranicu.
			return "redirect: sticker-list";
		}
		
		
		
		
		// ----------------------------- PRODUCT -------------------------------
		
		// kopirana odozgo sa stikera
		@RequestMapping(value="product-list")
		public String getProductList(Model model) { 
			// da bi prosledio stranici listu sitkera treba da napravim model pa preko njega prenosim podatke 
			List<Product> list = productDAO.getProductList();
			model.addAttribute("products",list);
							// pod ovim nazivom daljem podatke na stranicu.
			return "product-list"; // tako cemo je nazvati. Prosledjujemo joj listu stikera.
		}
		
		
		// isto kopirana iz stikera, pa dodata Lista kategorija - potrebno za pravljnje proizvoda.
		// metoda za stranicu koja pravi novi red - kategoriju, i unosi u bazu:
		@RequestMapping(value="product-form")
		public String getProductForm(Model model) { // forma radi sa Objektom			

			// Posalji na stranicu prazan objekat, popunice se tamo seterima:
			model.addAttribute("product",new Product()); 
			
			List<Category> categories = categoryService.getCategoryList();
			model.addAttribute("categories",categories); // uz prazan obj proizvoda - posalji i listu kategorija, treba da izabere kateg za novi proizvod
			
			// isto kao sto smo uzeli listu kateg tako treba da uzmemo i listu stikera:
			List<Sticker> stickers = stickerDAO.getStickerList(); // sve pripremljene metode
			model.addAttribute("stickers",stickers); // pod nazivom 'stickers' na stranicu salje listu stikera
			
			return "product-form";
			
		}
		
	
		// isto kopirano sa stikera:
		// metoda za Cuvanje izmena, klikom na Sacuvaj product na formi:
		@RequestMapping(value="product-save")
		public String getProductSave(@ModelAttribute Product product ) { 
										// objekat koji je dosao sa forme na prethodnoj str, popunjen
			
				System.out.println(product.getTitle());
				System.out.println(product.getCategory()); // ovde je id = null! zato:
			
			Category category = categoryService.getCategoryById(product.getCategory().getId());
						// ovde je sad category ceo objekat iz baze
			product.setCategory(category); // tek sad je setovan i name i id
			
			
			// isto za stikere za koje smo dobili samo id, samo za stikere imamo listu:
			List<Sticker> stickers = new ArrayList<Sticker>();
			for(Sticker sticker : product.getStickers()) {
					// obilazim ovu listu koja je napravljena u proizvodu, koju je korisnik izabrao na 
					// formi za pravljenje proizvoda
					//  ali su u listi samo id-jevi a sada dohvatamo cele objekte za svaki id, 
					// za svaki id uzimamo stiker iz baze:
					Sticker tempSticker = stickerDAO.getStickerById(sticker.getId());
					stickers.add(tempSticker);
			}
			product.setStickers(stickers);
			
			productDAO.saveProduct(product);
			
			return "redirect: product-list"; // preusmeri na str: product-list
					// ako je samo: return "product-list"; onda treba da imamo spremnu listu proizvoda, 
					// jer to trazi ova str
		}
		
		
		// isto kopirano sa stikera: za Update proizvoda:
		@RequestMapping(value="product-update")
		public String getProductUpdate(@RequestParam int id, Model model) { // model - za postojecu kateg
			// Sticker  sticker = new Sticker(); // ne nova nego dohvatamo po id:
			Product product = productDAO.getProductById(id);
			
			
			// da se popuni lista kategorija u padajucoj listi:
			List<Category> categories = categoryService.getCategoryList();			
			model.addAttribute("categories", categories); 
						// u metodi product-form koristim categories pa mora da se navede naziv		
			model.addAttribute("product",product);
			
			// isto za stikere:
			List<Sticker> stickers = stickerDAO.getStickerList(); // uzme sve stikere iz baze
			model.addAttribute("stickers", stickers);
					
			
			return "product-form";
		}
		
		// kopirano iz stikera:
		@RequestMapping(value="product-delete")
		public String getProductDelete(@RequestParam int id) {
			
			productDAO.deleteProduct(id);
			
			// kad izbrise, vraca se na istu stranicu.
			return "redirect: product-list";
		}
		
		
		
		
		@RequestMapping(value="product-detail")
		public String getProductDetail(@RequestParam int id, Model model) {
				// Model jer treba da saljemo ceo proizvod
			
			Product p = productDAO.getProductById(id);
			model.addAttribute("product", p); // prosledim objekat na stranicu
			
			return "product-detail";
		}
		
		
		
		
		
		
		
		// ----------------- MESSAGES --------------------------------
		
		@RequestMapping(value="message-list")
		public String getMessageList(Model model) {
			List<Message> list = messageDAO.getAllMessages();
			model.addAttribute("messages", list); 
							// pod nazivom messages posalji na stranicu - listu
			return "message-list";  // naziv .jsp stranice
		}
		
		
		
		@RequestMapping(value="message-seen")
		public String getMessageSeenPage(@RequestParam int id) {

			Message message = messageDAO.getMessageById(id);
							// daj mi poruku na koju sam kliknuo
			message.setIsSeen(true);
			
			messageDAO.saveMessage(message);
			
			return "redirect: message-list";  // naziv .jsp stranice
				// moze i samo: return "message-list"; 
		}
		
		
		
		
		
		//-------------------- SLIDER ---------------------------------
		
		@RequestMapping(value="slider-list")
		public String getSliderList(Model model) { // model jer stranici treba lista slajdera
			
			model.addAttribute("sliders", sliderDAO.getSliderList());
			
			return "slider-list";
		}
		
		@RequestMapping(value="slider-form")
		public String getSliderForm(Model model) {
			// saljem prazan objekat klase Slider:
			
			model.addAttribute("slider",new Slider());
			
			return "slider-form";
		}
		
		
		@RequestMapping(value="slider-update")
		public String getSliderUpdate(@RequestParam int id, Model model) {
			// model treba da uzme slajder i da ga prosledi na stranicu
			
			Slider s = sliderDAO.getSliderById(id);
			
			model.addAttribute("slider",s);
			
			return "slider-form"; // vracamo u istu stranicu
		}
		
		
		@RequestMapping(value="slider-save")
		public String getSliderSave(@ModelAttribute Slider slider) {
			
			sliderDAO.saveSlider(slider);
			
			return "redirect: slider-list";
		}
		
		
		@RequestMapping(value="slider-delete")
		public String getDeleteSlider(@RequestParam int id) {
			
			sliderDAO.deleteSlider(id);
			
			return "redirect: slider-list";
		}
		
		
		
		
		// ----------------- StaticPages -----------------------------------------
		
		@RequestMapping(value="static-page-form")
		public String getStaticPage(@RequestParam String page, Model model) { 
						// prosledim naziv stranice kao String
						// sad nije dovoljan samo content nego treba model jer treba id stranice
			
			if(page.equalsIgnoreCase("about")) {
				
				model.addAttribute("staticPage",staticPageDAO.getStaticPage(1));
				
			}else if(page.equalsIgnoreCase("location")) {
				
				model.addAttribute("staticPage", staticPageDAO.getStaticPage(2));
				
			}
			
			return "static-page-form";
		}
		
		
		@RequestMapping(value="static-page-save")
		public String getStaticPageSave(@ModelAttribute StaticPage staticPage) {
			
			staticPageDAO.saveStaticPage(staticPage);
			
			return "static-page-form";
		}
		
		
		
		// ----------------- USERS PAGES ----------------------------------------------
		
		@RequestMapping(value="user-list")
		public String getUserListPage(Model model) { 
			
			model.addAttribute("users", userDAO.getAllUsers());
			
			return "user-list";			
		}
		
		@RequestMapping(value="user-enabled")
		public String getUserEnabled(@RequestParam String username){ // id korisnika 
			
			User user = userDAO.getUser(username); // napravili smo metodu
			
			user.setEnabled(!user.getEnabled()); // postavi stanje suprotno od postojeceg
			
			
			userDAO.saveUser(user);
			
			return "redirect: user-list";
		}
		
		
		
		@RequestMapping(value="user-delete")
		public String getUserDelete(@RequestParam String username) {
			
			userDAO.deleteUser(username);
			
			return "redirect: user-list";
		}
		
		
		@RequestMapping(value="user-form")
		public String getUserForm(Model model) { 
			
			User user = new User();
			
			model.addAttribute("user", user); // saljemo prazan objekat User
			
			model.addAttribute("userRoles", userDAO.getUserRoles()); // na stranici smo ga nazvali userRoles
			
			return "user-form";
		}
		
		@RequestMapping(value="user-save")
		public String getUserSave(@ModelAttribute User user) {
			
			user.setEnabled(true); // podrazumevano je false u Javi ako se ne postavi.
			
			// pravimo metodu za ovo. u klasi User:
			// String password = new BCryptPasswordEncoder().encode(user.getPassword());  // enkript pre unosa u bazu
			// user.setPassword("{bcrypt}"+password);
			// nego:
			user.generateBCryptPassword();
			
			userDAO.saveUser(user);
			
			return "redirect: user-list";
		}
		
		
		@RequestMapping(value="user-update")
		public String getUserUpdate(@RequestParam String username, Model model) {
						// username param je Koji korisnik se menja
						// model da posaljem korisnika na stranicu
			model.addAttribute("user", userDAO.getUser(username));
			// da menja i rolu:
			model.addAttribute("userRoles",userDAO.getUserRoles());
			// jos i da na stranici vidim i da li je admin:
			model.addAttribute("isAdmin",true);
			
			return "user-form-update";
		}
		
		
		@RequestMapping(value="user-myupdate") // na YourProfile
		public String getUserMyUpdate(Principal principal, Model model) {
						// principal iz java.security  cuva sve info za logovanje:
			model.addAttribute("user", userDAO.getUser(principal.getName())); // daj ime ulogovanog
			
			return "user-form-update";
		}
		
		
		
		@RequestMapping(value="user-change-password")
		public String getChangePasswordPage(Principal principal, Model model) { 
						// lozinku menja onaj ko je ulogovan. za to koristim principal da dobijem ulogovanog
			model.addAttribute("changePassword", new ChangePassword());
			 
			return "user-form-change-password"; // imace polja old-pass, new-pass, confirm-pass
		}
		
		
		@RequestMapping(value="user-save-change-password")
		public String getChangePasswordSave(@ModelAttribute ChangePassword changePassword, Principal principal, Model model) {
			    // lozinku menja onaj ko je ulogovan. za to koristim principal da dobijem ulogovanog
				// model treba da se informacije salju dalje
			
			User user = userDAO.getUser(principal.getName()); // da dohvatim ulogovanog korisnika za dalji rad
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // da mogu da poredim enkript
						// koristim enkoder da proverava. jer proveravam text u polju sa textom u bazi koji je kriptovan
						// zato enkoder proverava da li enkripcija odgovara textu. vidi u ovoj metodi: else if()
			
			// ovo dalje moze i validatorom ali radimo ovako:
			if(!changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) { // ako nije isti:
				model.addAttribute("message", "Nova lozinka i potvrda nove lozinke se ne poklapaju.");
				model.addAttribute("changePassword", changePassword); // saljem lozinku ponovo na stranicu da se ne zagubi
				 
				// System.out.println("Nova lozinka i potvrda nove lozinke se ne poklapaju");
				
			}else if(!encoder.matches(changePassword.getOldPassword(), user.getEncodedPassword())) { // ako stara lozinka ne odgovara korisniku
					// sirova lozinka uneta u polje, poredi se sa enkodovanom lozinkom iz korisnika - iz baze. 
					// posto je u bazi lozinka sa predznacima: {bcrypt} napravili smo metodu koja vrati lozinku bez ovih znakova.
					// u User.java:	public String getEncodedPassword() { return password.replace("{bcrypt}", ""); }
				
				model.addAttribute("message", "Stara lozinka nije odgovarajuca.");
				model.addAttribute("changePassword", changePassword); // saljem lozinku ponovo na stranicu da se ne zagubi
				 
				// System.out.println("Stara lozinka nije odgovarajuca");
				
			}else { // sve je u redu:	
				
				user.setPassword(changePassword.getNewPassword()); // napravi novi
				user.generateBCryptPassword(); // enkriptuj
				userDAO.saveUser(user); // sacuvaj 
				
				model.addAttribute("message", "Uspesno ste promenili lozinku.");
				model.addAttribute("changePassword", new ChangePassword()); 
							// saljem prazan, sve na null, ako hoce neka pravi ponovo
				
				// System.out.println("Uspesno ste promenili lozinku.");
				
			}
						

			return "user-form-change-password";
			
		}
		
}
