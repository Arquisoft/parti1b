package asw.dto.DBManagement;

import asw.dto.model.CitizenDB;
import asw.participants.acceso.ChangePassword;

/**
 * 
 * @author Ramon Sobrino Llorca
 *
 */
public interface UpdateInfo{

	public boolean UpdateCitizen(CitizenDB ciudadano);
	public CitizenDB UpdateCitizen(ChangePassword info);
}
