package asw.DBManagement.services;

import asw.DBManagement.model.CitizenDB;

public interface CitizenDBService {

	//public CitizenDB getByLogin(String login);
	public CitizenDB findByMail(String email);
	public CitizenDB createCitizenDB(CitizenDB citizenDB);
}
