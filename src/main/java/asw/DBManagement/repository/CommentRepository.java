package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> findByIdSugerencia(Suggestion idSugerencia);

	List<Comment> findByCitizenDB(CitizenDB citizenDB);

}
