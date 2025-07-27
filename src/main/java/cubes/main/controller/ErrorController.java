package cubes.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value="errors")
	public String getErrorPage(HttpServletRequest request, Model model) {
		//	Model treba jer cemo da saljemo na ovu str Koja se greska desila (?)
		
		int statusCode = getErrorCode(request);
		
		model.addAttribute("statusCode", statusCode);
		
		switch(statusCode) {
		case 400:
			 
			model.addAttribute("errorMessage","Poslali ste los HttpRequest.");
			
			break;
		case 404:
			 
			model.addAttribute("errorMessage","Uneli ste adresu koja ne postoji.");
			
			break;
			
		default:
			
			model.addAttribute("errorMessage","Doslo je do greske.");
		}
		// moze i boja da se menja npt 500 crvena   400 zuta.
		
		// moze npr. 400<errorCode <500  vrati stranicu error-page-404, 
		// ako je >500 vrati stranicu error-page-500
		//
		return "error-page";
	}
	
	
	
	private int getErrorCode(HttpServletRequest httpRequest) {
		
		// vraca Kod greske koja se desila
		return (Integer)httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
