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
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteComment;
import asw.DBManagement.services.CommentsService;
import asw.DBManagement.services.SuggestionService;
import asw.DBManagement.services.VoteCommentService;
import asw.controllers.util.censura.Censura;
import asw.kafka.producers.KafkaProducer;


@Scope("session")
@Controller
public class CommentController {

	@Autowired
	private CommentsService commentsService;	
	@Autowired
	private VoteCommentService voteCommentService;
	@Autowired
	private SuggestionService suggestionService;
	private Censura censura = new Censura();
	

	@Autowired
	private KafkaProducer kafkaProducer;
	
	private List<Comment> comments = new ArrayList<Comment>();

	public void setCommentService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	@RequestMapping(value="/User/comment/commentSuggestion")
	public String commentSuggestion( @RequestParam String comentario, HttpSession session){

		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		Suggestion suggestion = (Suggestion) session.getAttribute("sugerencia");
		comentario = censura.censurar(comentario);
		Comment comment = new Comment((long)comments.size()+1, user, suggestion, comentario);

		commentsService.createComment(comment);
		comments = (List<Comment>) commentsService.findBySuggestion(suggestion);

		session.setAttribute("sugerencia", suggestion);
		session.setAttribute("comments", commentsService.findBySuggestion(suggestion));

		kafkaProducer.sendNewComentario(comment);

		return "User/comment";

	}

	@RequestMapping(value="User/comment")
	public String showComments(Long id_sug,String comment,HttpSession session){
		
		Suggestion suggestion = suggestionService.findById(id_sug);
		comments = (List<Comment>) commentsService.findBySuggestion(suggestion);
		session.setAttribute("sugerencia", suggestion);
		session.setAttribute("comments", commentsService.findBySuggestion(suggestion));

		return "User/comment";
	}

	@RequestMapping(value="/votaPosComment")
	public String votePosComment(String id_con , HttpSession session){

		Comment comment = commentsService.findById(Long.parseLong(id_con));
		CitizenDB ciudadano = (CitizenDB) session.getAttribute("usuario");

		VoteComment vc = voteCommentService.findByCitizenDBAndComment(ciudadano, comment);
		if(vc == null){
			vc = new VoteComment(comment, ciudadano);
			voteCommentService.createVoteComment(vc);
			comment.setNumero_votos(comment.getNumero_votos()+1);
			commentsService.update(comment);
			session.setAttribute("comments", commentsService.findBySuggestion((Suggestion) session.getAttribute("sugerencia")));
		}

		return "User/comment";
	}

	@RequestMapping(value="/votaNegComment")
	public String voteNegComment(String id_con , HttpSession session){

		Comment comment = commentsService.findById(Long.parseLong(id_con));
		CitizenDB ciudadano = (CitizenDB) session.getAttribute("usuario");

		VoteComment vc = voteCommentService.findByCitizenDBAndComment(ciudadano, comment);
		if(vc == null){
			vc = new VoteComment(comment, ciudadano);
			if(comment.getNumero_votos() > 0){
				voteCommentService.createVoteComment(vc);
				comment.setNumero_votos(comment.getNumero_votos()-1);
				commentsService.update(comment);
				session.setAttribute("comments", commentsService.findBySuggestion((Suggestion) session.getAttribute("sugerencia")));

			}
		}

		return "User/comment";
	}

}
