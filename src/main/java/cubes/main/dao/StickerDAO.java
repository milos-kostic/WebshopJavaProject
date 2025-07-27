package cubes.main.dao;

import java.util.List;
 
import cubes.main.entity.Sticker;

public interface StickerDAO {


	 public List<Sticker> getStickerList(); // Sticker je nova klasa model.	 
	 				// interfejs je, samo potpis metode ne i def
	 
	 public void saveSticker(Sticker sticker);
	 
	 public Sticker getStickerById(int id);
	 
	 public void deleteSticker(int id); // moze i ceo stiker a moze i id
}
