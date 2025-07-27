package cubes.main.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	
	@ExceptionHandler  // desi se svaki put kad se desi bilo koja greska
	public ResponseEntity<MessageResponse> handleException(Exception exception){	// mi smo nazvali metodu
						// ErrorResponse smo pravili klasu
						// Exception exception je da hvata bilo koji tip izuzetka
		
		MessageResponse response = new MessageResponse (     
							HttpStatus.BAD_REQUEST.value(),
							"Doslo je do greske: " + exception.getMessage(),
							System.currentTimeMillis()
							);  						// (code, message,time);
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	
	@ExceptionHandler  								// ova hvata samo kad se desi CategoryException:
	public ResponseEntity<MessageResponse> handleException(CategoryException exception){	 
					 
		MessageResponse response = new MessageResponse (     
							HttpStatus.BAD_REQUEST.value(),
						exception.getMessage(),
							System.currentTimeMillis()
							);  						// (code, message,time);
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	
}
