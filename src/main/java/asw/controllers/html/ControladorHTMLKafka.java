package asw.controllers.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import asw.DBManagement.model.Suggestion;
import asw.controllers.util.estadistica.Estadistica;
import asw.controllers.util.estadistica.EstadisticaService;
import asw.kafka.listeners.KafkaTopics;
import asw.kafka.listeners.MessageListener.SugerenceEvent;
import asw.kafka.listeners.MessageListener.VoteEvent;

@Controller
public class ControladorHTMLKafka {

	 private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>()); 
	
		
	@Autowired
	private EstadisticaService estatService;
	


	public List<Estadistica> popularidadSugerencia(List<Suggestion> sugerencia) {
		return estatService.listaPopularidadSugerencia(sugerencia);
	}


	
	
	@RequestMapping( value = "/newSugerence")
	@EventListener
	public void newSugerence(Suggestion data){
		
		System.out.println("Evento escuchado!");
		SseEventBuilder newSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"newSugerence\" , \"title\":\"" + data.getTitle() + "\"}");
		sendEvent(newSugerenceEvent);
	}
	
	@RequestMapping( value = "/editComentary")
	@EventListener
	public void newComentary(SugerenceEvent data){
		if(data.getTipo()==KafkaTopics.DELETE_SUGERENCE)
		{	
			SseEventBuilder newComentaryEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"deleteSugerence\" ,  \"title\":\"" + data.getTitulo() +"\" }");
			sendEvent(newComentaryEvent);
		}
		else if(data.getTipo()==KafkaTopics.SUGERENCE_EDIT)
		{
			SseEventBuilder newComentaryEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"editSugerence\" ,  \"title\":\"" + data.getTitulo() +"\",  \"antiguo\":\"" + data.getAntiguo() +"\" }");
			sendEvent(newComentaryEvent);
		}
	}
	
	@RequestMapping( value = "/newComentary")
	@EventListener
	public void newComentary(VoteEvent data){
		if(data.getTipo()==KafkaTopics.NEW_COMENTARY)
		{	
			SseEventBuilder newComentaryEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"newComentary\" ,  \"title\":\"" + data.getTitulo() +"\" }");
			sendEvent(newComentaryEvent);
		}
	}
	
	@RequestMapping( value = "/upvoteSugerence")
	@EventListener
	public void upvoteSugerence(VoteEvent data){
		if(data.getTipo()==KafkaTopics.UPVOTE_SUGERENCE)
		{
			SseEventBuilder upvoteSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"upvote\" , \"title\":\"" + data.getTitulo() + "\" }");
			sendEvent(upvoteSugerenceEvent);
		}
	}
	
	@RequestMapping( value = "/downvoteSugerence")
	@EventListener
	public void downvoteSugerence(VoteEvent data){
		if(data.getTipo()==KafkaTopics.DOWNVOTE_SUGERENCE)
		{
			SseEventBuilder downvoteSugerenceEvent = SseEmitter.event().name("evento").data("{ \"tipo\": \"downvote\" , \"title\":\"" + data.getTitulo() + "\" }");
			sendEvent(downvoteSugerenceEvent);
		}
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
