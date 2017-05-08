package asw.DBManagement.services;

import asw.DBManagement.model.CitizenDB;

public interface CitizenDBService {

	public CitizenDB findByMail(String email);
	public CitizenDB createCitizenDB(CitizenDB citizenDB);
}
