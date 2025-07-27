package cubes.main.controller;

import java.util.Arrays;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cubes.main.dao.MessageDAO;
import cubes.main.dao.ProductDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.StaticPageDAO;
import cubes.main.dao.StickerDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Message;
import cubes.main.entity.Product;
import cubes.main.entity.Slider;
import cubes.main.entity.Sticker;
import cubes.main.service.CategoryService;


@Controller
public class FrontController {

	
		@Autowired
		private MessageDAO messageDAO;
	
		@Autowired
		private ProductDAO productDAO;
		
		@Autowired
		private CategoryService categoryService; // posto smo ga vec napravili, koristimo servis
		
		@Autowired
		private SliderDAO sliderDAO;
		
		@Autowired
		private StaticPageDAO staticPageDAO;
		
		@Autowired
		private StickerDAO stickerDAO; 
		
		
		//-------------- HOMEPAGE --------------------------------------
		
		@RequestMapping(value={"/homepage","","/"})
		public String getHomepage(Model model) {
			
			List<Product> list = productDAO.getProductListForMainPage();
			 
				// List<Category> categories = categoryService.getCategoryList(); 
					// vratio bi sve, nego smo pravili novu metodu da vrati samo one sa naslovne str:
			List<Category> categories = categoryService.getCategoryListForMainPage(); 
			
			// 
			List<Slider> sliders = sliderDAO.getSliderList();
			
			
			
			model.addAttribute("products", list); 
						// saljem listu  pod nazivom products, 
			model.addAttribute("categories", categories);
			
						// i slajdere:
			model.addAttribute("sliders",sliders);
			
			
			
			 return "homepage"; 
		}
		
		
		
		
		
		
		
		//-------------- CONTACT ---------------
	
		@RequestMapping(value="/contact-form")
		public String getContactPage(Model model) {			
			model.addAttribute("message", new Message()); 			
			return "contact-form";
		}
		
		
		@RequestMapping(value="/contact-save")
		public String getContactSavePage(@ModelAttribute Message message) {
			
			messageDAO.saveMessage(message);
			
			return "redirect: contact-form";
		}
		
		
		
		
		//------------- ABOUT US ------------------
		
		@RequestMapping(value="/about-us")
		public String getAboutUsPage(Model model) {
				// staticka stranica			
			
			// treba da prosledim stranici model
			
			model.addAttribute("content",staticPageDAO.getAboutUsPageContent());
				// metoda getAboutUsPageContent() vratice mi sadrzaj stranice AboutUs
				// pod nazivom "content" saljem sadrzaj kao parametar na stranicu]\
			
			return "about-us";
		}
		
		
		
		
		
		//-------------- LOCATION --------------------------------------
		@RequestMapping(value="/location")
		public String getLocationPage(Model model) { 
				// model, pa kasnije cu stranici proslediti podatke:
				// 		da se iz baze listaju lokacije (Lista lokacija)
				// kasnije
			return "location";
		}
		
		
		
		
		//--------------- SHOP ------------------------------------------
		@RequestMapping(value="/shop-list")
		public String getShopListPage(
						@RequestParam(required = false) Integer category, 
						@RequestParam(required = false) Integer price, 
						@RequestParam(required = false, value="sticker") Integer[] stickersArray, 
														// value je pod kojim nazivom vodi niz (?)
						Model model) {
			// na ovu stranicu prosledjujem proizvode.
			
			// List<Product> products = productDAO.getProductList(); // vraca sve proizvode
			List<Product> products = productDAO.getProductList(category, price, stickersArray); // ovo je categoryId  // metoda Filtrira proizvode.
			model.addAttribute("products", products);

			List<Category> categories = categoryService.getCategoryList();
			model.addAttribute("categories", categories);
			
			List<Sticker> stickers = stickerDAO.getStickerList();
			model.addAttribute("stickers", stickers);
			
			 
			 
		
//			if(stickersArray!=null) {
//				System.out.println("Broj parametara: " + stickersArray.length);
//				for(int i=0;i<stickersArray.length;i++) {
//					System.out.println("Stiker: "+stickersArray[i]);
//				}
//			}

			if(category!=null) {
				model.addAttribute("categorySelected",category); // saljem na stranicu kategoriju koja je izabrana
			}
			if(price!=null) {
				model.addAttribute("priceSelected",price);
			}
			// stikeri:
			if(stickersArray!=null) { //  Integer[] stickersArray
				model.addAttribute("stickersSelected", Arrays.asList(stickersArray));  
				// nije htelo sa: , stickersArray);   jer je na stranici metoda .contains koja radi sa listama
				// Liste imaju metodu .contains a sa nizom bi trebalo rucno
			}
			
			
			return "shop-list";
		}
		
		
		
		 
		// @RequestMapping(value="/shop-detail")
		@RequestMapping(value="/shop-detail/{title}/{id}")
		public String getShopDetailPage(  				// value je pod kojim nazivom vodi niz (?)
					// @RequestParam int id, Model model) {
					@PathVariable int id, @PathVariable String title, Model model) { // title ne mora 
			// kad sam izmenio link na homepage: shop-detail/${product.seoTitle}/${product.id}
			//  vise ne treba @RequestParam int id parametar nego: @PathVariable int id
			// menjam da akcija hvata sve url koji Pocinju sa /shop-detail:  "/shop-detail/{title}/{id}"
			
			Product p = productDAO.getProductById(id);
			model.addAttribute("product", p);	
			
			// za deo pri dnu koji prikazuje recently viewed, mi listamo proizvode iz kategorije: 
			List<Product> productsFromCategory = productDAO.getProductList(p.getCategory().getId(),null,null);
			// 	getProductList(category,price,sticker)  jer nam je to jedina metoda koja vraca proizvode po kateg.
			model.addAttribute("products",productsFromCategory);
			 
			return "shop-detail";
		}
		
		
		@RequestMapping(value="/shop-search")
		public String getShopSearchPage(@RequestParam String text, Model model) {
			
			//	System.out.println(text); // da li tekst sa stranice ododje do metode kontrolera
			
			List<Product> products = productDAO.getProductList(text); // biram ovu sa text parametrom
			
			model.addAttribute("products",products);
			
			return "shop-search"; // nova stranica za proizvode koji su rezultat pretrage, bez filtera
		}
		
		
		
		
		
		// ---------------------  RestAPI:  ---------------------------------------------------
		
		//   vraca ModelAndView. Radi isto sto i metoda iznad: getShopSearchPage
		@RequestMapping(value="/shop-search-test")
		public ModelAndView getShopSearchPageTest(@RequestParam String text) {
			
			List<Product> list = productDAO.getProductList(text);
			
		System.out.println("Lista: " + list.toString());
		
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("shop-search"); // naziv stranice koja prikazuje podatke
			
			mv.addObject("products", list); // nije addAttribute nego Objekat koji ima podatke
									
			return mv;
			
		}
		
		
		@RequestMapping(value="/shop-search-text")
		@ResponseBody // znaci da vrati String kao odgovor, a ne kao naziv jsp stranice
		public String getShopSearchPageText(@RequestParam String text) { 
			// String kao rezultat znaci da ce da vrati naziv jsp stranice
			
			// mozemo rucno da sastavimo html I vratimo kao rezultat
			
			// Namena je da tekst koji vracam budu Sirovi podaci za dalju upotrebu:
			
			List<Product> list = productDAO.getProductList(text);
			
			return list.toString(); // ovako ce pokusati da nadje .jsp stranicu koja se zova kao sadrzaj param text
			
		}
		
}
