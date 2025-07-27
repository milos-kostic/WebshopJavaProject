package cubes.main.rest;

// greske za kategorije:
public class CategoryException extends RuntimeException{

	
	public CategoryException() {
		 // ako ne prosledim nikakvu poruku: neka generalna poruka:
		super("Doslo je do problema sa ucitavanjem kategorije.");
		
	}
	
	// konstruktor sa perametrom:
	public CategoryException(String message) {
		super(message);
	}
}
