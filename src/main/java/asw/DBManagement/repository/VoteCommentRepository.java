package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.VoteComment;

@Repository
public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByCommentEquals(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	VoteComment findByCitizenDBAndComment(CitizenDB citizen, Comment comment);
	
}
