package asw.DBManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.DBManagement.model.CitizenDB;


@Repository
public interface CitizenDBRepository extends CrudRepository<CitizenDB, Long>{
	CitizenDB findByMail(String mail);
	//CitizenDB findByLogin(String login); //No teneis ningun campo login en ciudadano ¿?

	//CitizenDB findByLogin(String login);
}
