package asw.DBManagement.DBManagementParticipants;

import asw.DBManagement.model.CitizenDB;
import asw.participants.changeInfo.ChangePassword;

/**
 * 
 * @author Ramon Sobrino Llorca
 *
 */
public interface UpdateInfo{

	public boolean UpdateCitizen(CitizenDB ciudadano);
	public CitizenDB UpdateCitizen(ChangePassword info);
}
