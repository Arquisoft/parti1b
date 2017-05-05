package asw.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import asw.dto.model.Comment;
import asw.dto.model.Suggestion;
import asw.dto.repository.CommentRepository;
import asw.dto.repository.SuggestionRepository;
import asw.participants.acceso.ControladorHTML;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    @KafkaListener( topics = KafkaTopics.NEW_COMENTARY)
    public void listenComentarios(String data) {
    	
    	try {
			Comment comentario = mapper.readValue(data, Comment.class);
			logger.info("*****************\n"+"Comentario: "+comentario.getText());
			publisher.publishEvent(comentario);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
        logger.info("New message received: \"" + data + "\"");
    }
    
    @KafkaListener( topics = KafkaTopics.UPVOTE_SUGERENCE)
    public void listenApoyo(String data) {
    	try {
			Comment comentario = mapper.readValue(data, Comment.class);
			logger.info("*****************\n"+"Apoyo: "+comentario.getSuggestion().getTitle());
			publisher.publishEvent(new VoteEvent(comentario.getSuggestion().getTitle()));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.info("New message received: \"" + data + "\"");
    }
    
    @KafkaListener( topics = KafkaTopics.DOWNVOTE_SUGERENCE)
    public void listenDesacuerdo(String data){
    	try {
			Comment comentario = mapper.readValue(data, Comment.class);
			logger.info("*****************\n"+"Desacuerdo: "+comentario.getSuggestion().getTitle());
			publisher.publishEvent(new VoteEvent(comentario.getSuggestion().getTitle()));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.info("New message received: \"" + data + "\"");
    }
    
    public class VoteEvent{
    	private String titulo;
    	
    	public VoteEvent(String titulo){
    		this.titulo = titulo;
    	}
    	
    	public String getTitulo(){ return this.titulo; }
    }
    
    @Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.publisher = eventPublisher;
		
	}

}
