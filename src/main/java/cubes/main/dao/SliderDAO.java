package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Slider;
import cubes.main.entity.Sticker;

public interface SliderDAO {
 

	 public List<Slider> getSliderList(); // Sticker je nova klasa model.	 
	 				// interfejs je, samo potpis metode ne i def
	 
	 public void saveSlider(Slider s);
	 
	 public Slider getSliderById(int id);
	 
	 public void deleteSlider(int id); // moze i ceo stiker a moze i id
	 
	 
}
