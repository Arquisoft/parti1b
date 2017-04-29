package asw.factory;


import asw.dto.services.CitizenDBService;
import asw.dto.services.CommentsService;
import asw.dto.services.SuggestionService;
import asw.dto.services.VoteCommentService;
import asw.dto.services.VoteSuggestionService;
import asw.dto.services.CitizenDBServiceImpl;
import asw.dto.services.impl.CommentServiceImpl;
import asw.dto.services.impl.SuggestionServiceImpl;
import asw.dto.services.impl.VoteCommentServiceImpl;
import asw.dto.services.impl.VoteSuggestionServiceImpl;

public class Services {
	
	public static CitizenDBService getCitizenDBService(){
		return new CitizenDBServiceImpl();
	}
	
	public static SuggestionService getSuggestionService(){
		return new SuggestionServiceImpl();
	}
	
	public static CommentsService getCommentsService(){
		return new CommentServiceImpl();
	}
	
	public static VoteCommentService getVoteCommentService(){
		return new VoteCommentServiceImpl();
	}
	
	public static VoteSuggestionService getVoteSuggestionService(){
		return new VoteSuggestionServiceImpl();
	}

}
