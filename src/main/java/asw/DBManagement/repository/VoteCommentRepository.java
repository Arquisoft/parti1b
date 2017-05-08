package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.VoteComment;

public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByCommentEquals(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);

	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey); //No hay atributo voteCommentKey ¿?
	
}
