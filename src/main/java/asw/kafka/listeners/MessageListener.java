package asw.kafka.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import asw.DBManagement.model.Suggestion;

import java.io.IOException;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener implements ApplicationEventPublisherAware{

    @Autowired
    private ObjectMapper mapper;

    private static final Logger logger = Logger.getLogger(MessageListener.class);
    private ApplicationEventPublisher publisher;

    @KafkaListener(topics = KafkaTopics.NEW_SUGERENCE)
    public void listenSugerencias(String data) {
    	
    	try {
			Suggestion sugerencia = mapper.readValue(data, Suggestion.class);
			logger.info("*****************\n"+"Sugerencia: "+sugerencia.getTitle());
			publisher.publishEvent(sugerencia);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
    @KafkaListener(topics = KafkaTopics.SUGERENCE_EDIT)
    public void listenEditSugerencias(String data) {
    	String[] titulos = data.split("%");
		logger.info("*****************\n"+"Sugerencia: "+titulos[0]);
		publisher.publishEvent(new SugerenceEvent(titulos[0],titulos[1], KafkaTopics.SUGERENCE_EDIT));
	}
    
    
    @KafkaListener(topics = KafkaTopics.DELETE_SUGERENCE)
    public void listenDeleteSugerencias(String data) {
    	
    	try {
			Suggestion sugerencia = mapper.readValue(data, Suggestion.class);
			logger.info("*****************\n"+"Sugerencia: "+sugerencia.getTitle());
			publisher.publishEvent(new SugerenceEvent(sugerencia.getTitle(), KafkaTopics.DELETE_SUGERENCE));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



    @KafkaListener( topics = KafkaTopics.NEW_COMENTARY)
    public void listenComentarios(String data) {
		logger.info("*****************\n"+"Comentario: "+data);
		publisher.publishEvent(new VoteEvent(data,KafkaTopics.NEW_COMENTARY));
	
        logger.info("New message received: \"" + data + "\"");
    }
    
    @KafkaListener( topics = KafkaTopics.UPVOTE_SUGERENCE)
    public void listenApoyo(String data) {
    	
			logger.info("*****************\n"+"Apoyo: "+data);
			publisher.publishEvent(new VoteEvent(data,KafkaTopics.UPVOTE_SUGERENCE));
	
        logger.info("New message received: \"" + data + "\"");
    }
    
    @KafkaListener( topics = KafkaTopics.DOWNVOTE_SUGERENCE)
    public void listenDesacuerdo(String data){
    
			logger.info("*****************\n"+"Desacuerdo: "+data);
			publisher.publishEvent(new VoteEvent(data,KafkaTopics.DOWNVOTE_SUGERENCE));
		
        logger.info("New message received: \"" + data + "\"");
    }
    
    public class VoteEvent{
    	private String titulo;
    	private String tipo;
    	
    	public VoteEvent(String titulo, String tipo){
    		this.titulo = titulo;
    		this.tipo = tipo;
    	}
    	
    	public String getTitulo(){ return this.titulo; }
    	public String getTipo(){ return this.tipo; }

    	
    }
    
    public class SugerenceEvent{
    	private String titulo;
    	private String antiguo;
    	private String tipo;
    	
    	public SugerenceEvent(String titulo, String tipo){
    		this.titulo = titulo;
    		this.tipo = tipo;
    	}
    	
    	public SugerenceEvent(String titulo, String antiguo, String tipo){
    		this.titulo = titulo;
    		this.tipo = tipo;
    		this.antiguo = antiguo;
    	}
    	
    	public String getTitulo(){ return this.titulo; }
    	public String getTipo(){ return this.tipo; }
    	public String getAntiguo(){ return this.antiguo; }

    	
    }
    
    @Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.publisher = eventPublisher;
		
	}

}
