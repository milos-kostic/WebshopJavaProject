package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="stickers")
public class Sticker {

	// isto je sifarnik, jednostavna.
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String color;
	
	
	
	
	public Sticker() {
		
	}
	public Sticker(String title, String color) {
		// super();  // nevidljiva linija, poziva konstruktor natklase. Uvek se izvrsi iako nije napisan
		this.title = title;
		this.color = color;
	}
	public Sticker(String id) { // posebno konstruktor za Stiker preko id kao String
			// za izbor na stranici
		this.id = Integer.valueOf(id);
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) { 
		// ostavljamo seter za id jer treba kad se radi update
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title+" - "+color+" - "+id; // super.toString();
	}
	
	
	
}
