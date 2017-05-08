package asw.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import asw.DBManagement.services.SuggestionService;



@Scope("session")
@Controller
public class UserController {

	private SuggestionService suggestionService;
	
	  @RequestMapping(value="/User/suggestion")
	    public String goMakeSuggestion(String id_sug,HttpSession session){
	    	return "User/suggestion";
	    }	
	
	  @RequestMapping(value="/User/home")
	  public String goUserHome(HttpSession session){
		  session.setAttribute("sugerencias",suggestionService.findAll());
		  return "User/homeUsuario";
	  }
}
