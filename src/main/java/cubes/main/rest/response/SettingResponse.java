package cubes.main.rest.response;

import java.util.List;

import cubes.main.entity.Category;
import cubes.main.entity.Sticker;

// klasa sluzi da grupisem kateg i stikere u Jedan response:
public class SettingResponse { // model koji cuva listu stikera i listu kateg

	private List<Sticker> stickers;
	private List<Category> categories;
	
	// ostaje podrazumevani konstruktor
	
	
	public List<Sticker> getStickers() {
		return stickers;
	}
	public void setStickers(List<Sticker> stickers) {
		this.stickers = stickers;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
