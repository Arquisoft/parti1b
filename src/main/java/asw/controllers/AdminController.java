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

import asw.dto.model.Comment;
import asw.dto.model.Suggestion;
import asw.dto.services.CitizenDBService;
import asw.dto.services.CommentsService;
import asw.dto.services.SuggestionService;
import asw.dto.services.VoteCommentService;
import asw.dto.services.VoteSuggestionService;

@Scope("session")
@Controller
public class AdminController {
	
	@Autowired
	private CitizenDBService citizenService;
	
	@Autowired
	private CommentsService commentService;
	
	@Autowired
	private SuggestionService suggestionService;
	
	private VoteCommentService vCommentService;
	
	private VoteSuggestionService vSuggestionService;
	
		
	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	private List<Comment> comments = new ArrayList<Comment>();
	
	 @RequestMapping(value="/admin/home")
	    public String adminHome(Model model){
	    	return "admin/home";
	    }
	 
	 @RequestMapping(value="/admin/edit")
	    public String adminEdit(String id_sug,HttpSession session){
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
		   return "admin/home";
	    }
	       	
		@RequestMapping(value="/admin/edit/editSuggestion")
    	public String editSuggestion(@RequestParam String titulo,
    			@RequestParam String contenido,HttpSession session){

    		Suggestion suggestion = (Suggestion) session.getAttribute("sugerencia");
    		suggestion.setTitle(titulo);
    		suggestion.setContent(contenido);
    		suggestionService.update(suggestion);
    		sugerencias = suggestionService.findAll();
  
    		session.setAttribute("sugerencias", sugerencias);
    		
    		
    		return "admin/home";
    	}

}
