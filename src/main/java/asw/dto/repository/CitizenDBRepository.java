package asw.dto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import asw.dto.model.CitizenDB;


@Repository
public interface CitizenDBRepository extends CrudRepository<CitizenDB, Long>{
	CitizenDB findByMail(String mail);
	//CitizenDB findByLogin(String login); //No teneis ningun campo login en ciudadano ¿?
}
