package asw.participants.acceso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import asw.controllers.SuggestionController;
import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Estadistica;
import asw.dto.model.Suggestion;
import asw.dto.repository.CitizenDBRepository;
import asw.dto.repository.SuggestionRepository;
import asw.estadistica.EstadisticaService;
import asw.listeners.MessageListener.VoteEvent;

@Controller
public class ControladorHTML {

	 private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>()); 

	
	@Autowired
	private CitizenDBRepository repositorio;
	
	@Autowired
	private SuggestionRepository sugRepos;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHTML(Model modelo){
		return "login";
	}


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String postHTML(@RequestBody String parametros, Model modelo){
		//parametros = email=nombre&password=contraseña
		String[] p = parametros.split("&");

		//Usuario en blanco
		if(p[0].length() <= 8){
			modelo.addAttribute("error", "Usuario en blanco.");
			return "error";
		}

		//Contraseña en blanco
		if(p[1].length() <= 9){
			modelo.addAttribute("error", "Contraseña en blanco.");
			return "error";
		}

		String email = p[0].split("=")[1];
		email = email.replace("%40", "@");
		String password = p[1].split("=")[1];

		//Comprobar los datos

		try{
			CitizenDB ciudadano = repositorio.findByMail(email);
			if (ciudadano!= null)
			{
				if(!ciudadano.getMail().equals(email))
				{
					modelo.addAttribute("error", "Email no coincide.");
					return "error";
				}

				if(!ciudadano.getPassword().equals(password))
				{
					modelo.addAttribute("error", "La contraseña no coincide con la del usuario.");
					return "error";
				}

				if(ciudadano != null){
					if(ciudadano.getType().equals("ADMIN"))
						return this.popularidadSugerencia(parametros, modelo);
					else
						return "User/homeUsuario";
				}
			}

			modelo.addAttribute("error", "Usuario no registrado.");
			return "error";

		}catch(Exception e){
			modelo.addAttribute("error", "Ha ocurrido en error al conseguir los datos del usuario.");
			return "error";

		}
	}

	@Autowired
	private EstadisticaService estatService;
	


	public List<Estadistica> popularidadSugerencia(List<Suggestion> sugerencia) {
		return estatService.listaPopularidadSugerencia(sugerencia);
	}


	@RequestMapping(path="/userPriv", method=RequestMethod.GET)
	public String popularidadSugerencia(@RequestBody String parametros, Model modelo) {
		List<Suggestion> sugerencias = (List<Suggestion>) sugRepos.findAll();
		List<Estadistica> estadisticas = estatService.listaPopularidadSugerencia(sugerencias);
		modelo.addAttribute("estadisticas",estadisticas);
		return "userPriv";
	}
	
	@RequestMapping( value = "/newSugerence")
	@EventListener
	public void newSugerence(Suggestion data){
		
		System.out.println("Evento escuchado!");
		SseEventBuilder newSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"newSugerence\" , \"title\":\"" + data.getTitle() + "\"}");
		sendEvent(newSugerenceEvent);
	}
	
	@RequestMapping( value = "/newComentary")
	@EventListener
	public void newComentary(Comment data){


		SseEventBuilder newComentaryEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"newComentary\" ,  \"title\":\"" + data.getSuggestion().getTitle() +"\" }");
		sendEvent(newComentaryEvent);
	}
	
	@RequestMapping( value = "/upvoteSugerence")
	@EventListener
	public void upvoteSugerence(VoteEvent data){
		SseEventBuilder upvoteSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"upvote\" , \"title\":\"" + data.getTitulo() + "\" }");
		sendEvent(upvoteSugerenceEvent);
	}
	
	@RequestMapping( value = "/downvoteSugerence")
	@EventListener
	public void downvoteSugerence(VoteEvent data){
		SseEventBuilder downvoteSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"downvote\" , \"title\":\"" + data.getTitulo() + "\" }");
		sendEvent(downvoteSugerenceEvent);
	}
	
	private void sendEvent(SseEventBuilder event){
		synchronized (sseEmitters) {
			for(SseEmitter emitter: sseEmitters){
				try {
					System.out.println("Enviando el evento");
					emitter.send(event);
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}
		}
	}
	
	@RequestMapping("/userPriv/updates")
	SseEmitter updateHTML() {
		SseEmitter sseEmitter = new SseEmitter();
		synchronized (this.sseEmitters) {
			this.sseEmitters.add(sseEmitter);
			sseEmitter.onCompletion(() -> {
				synchronized (this.sseEmitters) {
					this.sseEmitters.remove(sseEmitter);
				}
			});
		}
		return sseEmitter;
	}
	
}
