package asw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Suggestion;
import asw.dto.model.VoteSuggestion;
import asw.dto.services.SuggestionService;



@Scope("session")
@Controller
public class SuggestionController {

	//Descomentar cuando funciones service
	@Autowired
	private SuggestionService suggestionService;

	public void setSuggestionService(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	private List<Comment> comments = new ArrayList<Comment>();

	@RequestMapping(value="/User/suggestion/makeSuggestion")
	public String makeSuggestion(@RequestParam String titulo, @RequestParam String contenido, HttpSession session){

		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		Suggestion suggestion = new Suggestion((long)user.getSugerencias().size()+1,titulo, user);
		suggestion.setContent(contenido);
		//Esto cuando funcione el service
		suggestionService.createSuggestion(suggestion);
		sugerencias = suggestionService.findAll();
		session.setAttribute("sugerencias", sugerencias);

		// AHORA 
		session.setAttribute("sugerencias", sugerencias);

		return "User/homeUsuario";

	}

	@RequestMapping(value="/votaPosSuggestion")
	public String votePosSuggestion(String id_sug,HttpSession session){

		Suggestion suggestion = suggestionService.findById(Long.parseLong(id_sug));
		
		List<Suggestion> suggestions = (List<Suggestion>) session.getAttribute("sugerencias");
		for(Suggestion sug : suggestions)
			if(sug.getId() == Long.parseLong(id_sug))
				suggestion = sug;

		//boolean existe = false;		 	
		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		//for(VoteSuggestion voteSuggestion: user.getVotesSugerencias())
		//	if(voteSuggestion.getSuggestion().getId() == Long.parseLong(id_sug))
		//		existe = true;
		//if(!existe){

		//VoteSuggestion  voteSuggestion = new VoteSuggestion((long)1,user,suggestion);
		//List<Suggestion> aux = (List<Suggestion>) session.getAttribute("sugerencias");
		//for(Suggestion suggestion2 : aux)
		//if(suggestion2.getId() == Long.parseLong(id_sug)){ //sino nos quedaríamos en negativo en los votos
		suggestion.setNum_votes(suggestion.getNum_votes()+1);
		suggestionService.update(suggestion);
		//}
		//}
		session.setAttribute("sugerencias", suggestions);
		return "User/homeUsuario";
	}

	@RequestMapping(value="/votaNegSuggestion")
	public String voteNegSuggestion(String id_sug,HttpSession session){
		
		Suggestion suggestion = suggestionService.findById(Long.parseLong(id_sug));

		List<Suggestion> suggestions = (List<Suggestion>) session.getAttribute("sugerencias");
		for(Suggestion sug : suggestions)
			if(sug.getId() == Long.parseLong(id_sug))
				suggestion = sug;

		//boolean existe = false;		 	
		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		//for(VoteSuggestion voteSuggestion: user.getVotesSugerencias())
			//if(voteSuggestion.getSuggestion().getId() == Long.parseLong(id_sug))
			//	existe = true;
		//if(!existe){

			//VoteSuggestion  voteSuggestion = new VoteSuggestion((long)1,user,suggestion);
			List<Suggestion> aux = (List<Suggestion>) session.getAttribute("sugerencias");
			for(Suggestion suggestion2 : aux)
				if(suggestion2.getId() == Long.parseLong(id_sug)){
					if(suggestion2.getNum_votes()>0){//sino nos quedaríamos en negativo en los votos
						suggestion2.setNum_votes(suggestion2.getNum_votes()-1);
						suggestionService.update(suggestion2);
					}
				//}
		}
		session.setAttribute("sugerencias", suggestions);

		return "User/homeUsuario";
	}

	//De momento no funciona correctamente
	//    @RequestMapping(value="/User/suggestion")
	//    public String goMakeSuggestion(@RequestParam String id_sug,HttpSession session){
	//    	//de nuevo en este método
	//    	//sería lógico buscar la sugerencia
	//    	//por id a través de un servicio
	//    	//no obstante a falta de funcionamiento de los mismos iré
	//    	//buscando las sugerencias en la lista creada 
	//    	//en la misma session del usuario
	//    	Long id = Long.parseLong(id_sug);
	//		//Descomentar cuando solucionemos el error
	////    	Suggestion suggestion = new Suggestion();
	////    	suggestion = SuggestionService.findById(id);
	//		
	////    	session.setAttribute("sugerencia", suggestion);
	//    	
	//    	return "User/suggestion";
	//    }

}
