package asw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.services.CitizenDBService;
import asw.DBManagement.services.CommentsService;
import asw.DBManagement.services.SuggestionService;
import asw.DBManagement.services.VoteCommentService;
import asw.DBManagement.services.VoteSuggestionService;



@Scope("session")
@Controller
public class UserController {

	private CitizenDBService citizenService;
	private CommentsService commentService;
	private SuggestionService suggestionService;
	private VoteCommentService vCommentService;
	private VoteSuggestionService vSuggestionService;
	
	
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	
	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	private List<Comment> comments = new ArrayList<Comment>();
	
	  @RequestMapping(value="/User/suggestion")
	    public String goMakeSuggestion(String id_sug,HttpSession session){
	    	//de nuevo en este método
	    	//sería lógico buscar la sugerencia
	    	//por id a través de un servicio
	    	//no obstante a falta de funcionamiento de los mismos iré
	    	//buscando las sugerencias en la lista creada 
	    	//en la misma session del usuario
	    	return "User/suggestion";
	    }	
	
	  @RequestMapping(value="/User/home")
	  public String goUserHome(HttpSession session){
		  session.setAttribute("sugerencias",suggestionService.findAll());
		  return "User/homeUsuario";
	  }
}
