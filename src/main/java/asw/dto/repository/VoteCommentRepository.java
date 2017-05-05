package asw.dto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.VoteComment;
import asw.dto.model.key.VoteCommentKey;

public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByComment(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey); //No hay atributo voteCommentKey ¿?
	
}
