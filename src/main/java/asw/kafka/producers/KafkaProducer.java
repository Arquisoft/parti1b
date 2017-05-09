package asw.kafka.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;
import asw.kafka.listeners.KafkaTopics;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;

import javax.annotation.ManagedBean;

@ManagedBean
@EnableScheduling
public class KafkaProducer {

    private static final Logger logger = Logger.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper mapper;

    public void sendNewSuggestion(Suggestion suggestion) {
        String sugerenciaJSON = "";
       suggestion.setCitizenDB(null);
       suggestion.setComments(new ArrayList<Comment>());

        try {
            sugerenciaJSON = mapper.writeValueAsString(suggestion);
            send(KafkaTopics.NEW_SUGERENCE, sugerenciaJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendEditSuggestion(String suggestion) {
       send(KafkaTopics.SUGERENCE_EDIT, suggestion);
    }
    public void sendDeleteSuggestion(Suggestion suggestion) {
        String sugerenciaJSON = "";
       suggestion.setCitizenDB(null);
       suggestion.setComments(new ArrayList<Comment>());
       suggestion.setVoteSuggestions(new ArrayList<VoteSuggestion>());

        try {
            sugerenciaJSON = mapper.writeValueAsString(suggestion);
            send(KafkaTopics.DELETE_SUGERENCE, sugerenciaJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendNewComentario(Comment comment) {
      send(KafkaTopics.NEW_COMENTARY, comment.getSuggestion().getTitle());        
    }
    
    public void sendNewApoyoSugerencia(Suggestion sugerencia) {
       send(KafkaTopics.UPVOTE_SUGERENCE, sugerencia.getTitle());
    }
    
    public void sendNewContraSugenrencia(Suggestion sugerencia) {
        send(KafkaTopics.DOWNVOTE_SUGERENCE, sugerencia.getTitle());     
    }

    public void send(String topic, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info(result);
                logger.info("Success on sending message \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }
}