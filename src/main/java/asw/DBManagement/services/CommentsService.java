package asw.DBManagement.services;

import java.util.List;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;

public interface CommentsService {
	
	List<Comment> findBySuggestion(Suggestion suggestion);
	List<Comment> findByCitizenDB(CitizenDB citizenDB);
	List<Comment> findAll();
	Comment findById(long id);
	Comment createComment(Comment comment);
	void deleteComment(Comment comment);
}
