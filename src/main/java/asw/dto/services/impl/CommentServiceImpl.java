package asw.dto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Suggestion;
import asw.dto.repository.CommentRepository;
import asw.dto.services.CommentsService;

@Service
public class CommentServiceImpl implements CommentsService {

	private CommentRepository commentRepository;

	@Autowired
	public void setCommentRepository(CommentRepository commentRepository){
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> findBySuggestion(Suggestion idSugerencia) {
		return commentRepository.findByIdSugerencia(idSugerencia);
	}

	@Override
	public List<Comment> findByCitizenDB(CitizenDB citizenDB) {
		return commentRepository.findByCitizenDB(citizenDB);
	}

	@Override
	public List<Comment> findAll() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	@Override
	public void deleteComment(Comment comment) {
		 commentRepository.delete(comment);
	}

	@Override
	public Comment findById(long id) {
		return commentRepository.findOne(id);
	}

}
