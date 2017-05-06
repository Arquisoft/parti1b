package asw.dto.services;

import asw.dto.model.CitizenDB;

public interface CitizenDBService {

	//public CitizenDB getByLogin(String login);
	public CitizenDB findByMail(String email);
	public CitizenDB createCitizenDB(CitizenDB citizenDB);
}
