package asw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;
import asw.DBManagement.services.SuggestionService;
import asw.DBManagement.services.VoteSuggestionService;
import asw.controllers.util.censura.Censura;
import asw.kafka.producers.KafkaProducer;


@Scope("session")
@Controller
public class SuggestionController {

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private VoteSuggestionService voteSuggestionService;

	@Autowired
	private KafkaProducer kafkaProducer;
	private Censura censura = new Censura();

	public void setSuggestionService(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	public void setVoteSuggestionService(VoteSuggestionService voteSuggestionService) {
		this.voteSuggestionService = voteSuggestionService;
	}

	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();

	@RequestMapping(value="/User/suggestion/makeSuggestion")
	public String makeSuggestion(@RequestParam String titulo, @RequestParam String contenido, HttpSession session){

		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		titulo = censura.censurar(titulo);
		contenido = censura.censurar(contenido);
		Suggestion suggestion = new Suggestion((long)user.getSugerencias().size()+1,titulo, user);
		
		suggestion.setContent(contenido);
	
		suggestionService.createSuggestion(suggestion);
		sugerencias = suggestionService.findAll();
		session.setAttribute("sugerencias", sugerencias);
		kafkaProducer.sendNewSuggestion(suggestion);

		session.setAttribute("sugerencias", sugerencias);

		return "User/homeUsuario";

	}

	@RequestMapping(value="/votaPosSuggestion")
	public String votePosSuggestion(String id_sug,HttpSession session){

		Suggestion suggestion = suggestionService.findById(Long.parseLong(id_sug));
		CitizenDB ciudadano = (CitizenDB) session.getAttribute("usuario");

		VoteSuggestion vs = voteSuggestionService.findByCitizenDBAndSuggestion(ciudadano, suggestion);
		if(vs == null){
			vs = new VoteSuggestion(ciudadano, suggestion);
			voteSuggestionService.createVoteSuggestion(vs);
			suggestion.setNum_votes(suggestion.getNum_votes()+1);
			suggestionService.update(suggestion);
			session.setAttribute("sugerencias", suggestionService.findAll());
			kafkaProducer.sendNewApoyoSugerencia(suggestion);
		}
		return "User/homeUsuario";
	}

	@RequestMapping(value="/votaNegSuggestion")
	public String voteNegSuggestion(String id_sug,HttpSession session){

		Suggestion suggestion = suggestionService.findById(Long.parseLong(id_sug));
		CitizenDB ciudadano = (CitizenDB) session.getAttribute("usuario");

		VoteSuggestion vs = voteSuggestionService.findByCitizenDBAndSuggestion(ciudadano, suggestion);
		if(vs == null){
			vs = new VoteSuggestion(ciudadano, suggestion);
			if(suggestion.getNum_votes() > 0)
			{
				voteSuggestionService.createVoteSuggestion(vs);
				suggestion.setNum_votes(suggestion.getNum_votes()-1);
				suggestionService.update(suggestion);
				session.setAttribute("sugerencias", suggestionService.findAll());
				kafkaProducer.sendNewApoyoSugerencia(suggestion);
			}
		}
		return "User/homeUsuario";
	}
}
