package asw.controllers;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Suggestion;
import asw.dto.services.CitizenDBService;
import asw.dto.services.CommentsService;
import asw.dto.services.SuggestionService;
import asw.dto.services.VoteCommentService;
import asw.dto.services.VoteSuggestionService;
import asw.dto.services.impl.CitizenDBServiceImpl;




@Scope("session")
@Controller
//@RequestMapping("*")
public class MainController {
	
	
	//Descomentar cuando funciones service
	@Autowired
	private SuggestionService suggestionService;
	@Autowired
	private CitizenDBService citizenDBService;
	@Autowired
	private CommentsService commentsService;
	
	
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	private List<Comment> comments = new ArrayList<Comment>();
	
//	@Autowired
//	public void setCommentService(CommentsService commentService) {
//		this.commentService = commentService;
//	}
//
//	@Autowired
//	public void setCitizenService(CitizenDBService citizenService) {
//		this.citizenService = citizenService;
//	}
//
//	@Autowired
//	public void setSuggestionService(SuggestionService suggestionService) {
//		this.suggestionService = suggestionService;
//	}
//
//	@Autowired
//	public void setVoteCommentService(VoteCommentService vCommentService) {
//		this.vCommentService = vCommentService;
//	}
//	
//	@Autowired
//	public void setSystemService(VoteSuggestionService vSuggestionService) {
//		this.vSuggestionService = vSuggestionService;
//	}
	
    @RequestMapping(value="/")
    public String landing(HttpSession session, Model model) {
    	//crearUsuario();  da un error (Oliver), hay q revisarlo
    	    	
        return "/login";
       }
   
/* 
    @RequestMapping(value="/login")
    public String log(Model model){
    	return "login";
    }
    */
    
    @RequestMapping(value="/User/homeUsuario")
    public String logHtml(Model model,HttpSession session){
    	session.setAttribute("sugerencias",suggestionService.findAll());
    	return "User/homeUsuario";
    }
    
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
    CitizenDB user = citizenDBService.findByMail(email);
    user.setPassword(password);
    return "error";
   
    }
   
  

    	@RequestMapping(value="/cerrarSesion")
        public String logOut( HttpSession session){
         
    		session.setAttribute("usuario", null);  		
    		return "/login";
    	}
}