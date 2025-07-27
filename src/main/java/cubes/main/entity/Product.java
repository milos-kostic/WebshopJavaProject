package cubes.main.entity;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {

	// redom po kolonama u tabeli:
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String image;
				// slika moze: fajl ceo u bazi, tipa .blob, ili samo putanja.
				// ne cuvaju se celi fajlovi jer Usporava bazu i cini je mnogo vecom
				// praksa je da se fajlovi cuvaju negde na fajl-sistemu na serveru a u bazi
				// samo putanja
	private double price;
	private int quantity;	
	@Column // (name="homepage")
	private boolean homepage; // isHomepage
	
	// -------------------------------------
	// kategorija: veza je vise-na-jedan:
	@ManyToOne(cascade = {		
				CascadeType.DETACH, 
				CascadeType.MERGE,
				CascadeType.PERSIST,
				CascadeType.REFRESH}) 
		// necu da se brise kateg kad obrisem sve njene proizvode
	@JoinColumn(name="category_id") // citas iz baze, U tabeli Products Kako se zove kolona spoljni kljuc
	private Category category; // 1-vise, proizvod moze samo jednu kategoriju
	
	
	// --------------------------------------
	// stiker: // veza je vise-na-vise
	@ManyToMany(
			cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
		}
		//	, fetch = FetchType.EAGER  // kad uzmes proizvod uzmi i njegove stikere		
			) 
	// sve radi osim brisanja
	// 				vise-na-vise  ne moze anotacija  @JoinColumn  jer nije jedna vezna kolona, nego vezna Tabela
	@JoinTable(
			name="products_stickers", 
			joinColumns = @JoinColumn(name="product_id"),
			inverseJoinColumns = @JoinColumn(name="sticker_id"))  
	// 				nego ide:  @JoinTable. name = je naziv vezne tabele
	private List<Sticker> stickers; 
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="manual_id")
	private Manual manual;
	
	
	public Product() {
		
	}
	public Product(String title, String description, String image, double price) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
		this.price = price;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
	
	public Category getCategory() {
		
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
		
	
	
	
	public List<Sticker> getStickers() {
		return stickers;
	}
	public void setStickers(List<Sticker> stickers) {
		this.stickers = stickers;
	}
	
	
	public Manual getManual() {
		return manual;
	}
	public void setManual(Manual manual) {
		this.manual = manual;
	}
	
	
	
	public boolean getHomepage() {
		return homepage;
	}
	public void setHomepage(boolean homepage) {
		this.homepage = homepage;
	}
	
	
	public Sticker getRandomSticker() {
		if(stickers == null || stickers.size()==0) {
			// return new Sticker(); // ako je prazna da ne vrati nista:
			return null;
		}
		
		//return stickers.get(0); // vrati prvi ako imam listu stikera // ili:
		
		Random random = new Random();
		Sticker s = stickers.get(random.nextInt(stickers.size()));
		
		return  s; // stickers.get(random.nextInt(stickers.size()));
		
		
	}
	
	
	
	public String getFormattedPrice() {
		
		// napraviti string i formatirati kako zelimo.
		
		return String.valueOf(price);
	}
	
	
	public String getSeoTitle() {
		
		return title.replaceAll(" ", "-").toLowerCase(); // gde god je space zaneni sa -
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title+" - "+id; // super.toString();
	}
	
	
}
