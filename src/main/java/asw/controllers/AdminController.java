package asw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.services.CommentsService;
import asw.DBManagement.services.SuggestionService;
import asw.kafka.producers.KafkaProducer;

@Scope("session")
@Controller
public class AdminController {
	
	@Autowired
	private CommentsService commentService;
	
	@Autowired
	private SuggestionService suggestionService;
	
	@Autowired
	private KafkaProducer kafkaProducer;
		
	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	
	 @RequestMapping(value="/admin/home")
	    public String adminHome(Model model){
	    	return "admin/home";
	    }
	 
	 @RequestMapping(value="/admin/edit")
	    public String adminEdit(String id_sug,HttpSession session){
	     	@SuppressWarnings("unchecked")
			List<Suggestion> aux = (List<Suggestion>) session.getAttribute("sugerencias");
	    	for(Suggestion sug : aux)
	    		if(sug.getId() == Long.parseLong(id_sug)){
	    			session.setAttribute("sugerencia", sug);
	    			session.setAttribute("titulo", sug.getTitle());
	    		}
	    	return "admin/edit";
	    }
	 
	   @RequestMapping(value="/borrar")
	    public String borrar(String id_sug,HttpSession session){
		   Suggestion suggestion = suggestionService.findById(Long.parseLong(id_sug));
		   
		   for(Comment c : suggestion.getComments())
			   commentService.deleteComment(c);
		   
		   suggestionService.deleteSuggestion(suggestion);
		   session.setAttribute("sugerencias", suggestionService.findAll());
	    	this.kafkaProducer.sendDeleteSuggestion(suggestion);
		   return "admin/home";
	    }
	       	
		@RequestMapping(value="/admin/edit/editSuggestion")
    	public String editSuggestion(@RequestParam String titulo,
    			@RequestParam String contenido,HttpSession session){

    		Suggestion suggestion = (Suggestion) session.getAttribute("sugerencia");
    		String tituloAnt= suggestion.getTitle();
    		suggestion.setTitle(titulo);
    		suggestion.setContent(contenido);
    		suggestionService.update(suggestion);
    		sugerencias = suggestionService.findAll();
  
    		session.setAttribute("sugerencias", sugerencias);
	    	this.kafkaProducer.sendEditSuggestion(tituloAnt+"%"+suggestion.getTitle());

       		return "admin/home";
    	}

}
