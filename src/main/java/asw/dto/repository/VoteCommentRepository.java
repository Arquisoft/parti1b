package asw.dto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.VoteComment;

public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByCommentEquals(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey); //No hay atributo voteCommentKey ¿?
	
}
