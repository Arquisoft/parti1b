package asw.dto.services;

import java.util.List;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Suggestion;

public interface CommentsService {
	
	List<Comment> findBySuggestion(Suggestion suggestion);
	List<Comment> findByCitizenDB(CitizenDB citizenDB);
	List<Comment> findAll();
	Comment fingById(long id);
	Comment createComment(Comment comment);
	void deleteComment(Comment comment);
}
