package asw.dto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> findByIdSugerencia(Suggestion idSugerencia);

	List<Comment> findByCitizenDB(CitizenDB citizenDB);

}
