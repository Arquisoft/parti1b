package asw.dto.DBManagement;

import asw.dto.DBManagement.model.Ciudadano;
import asw.participants.acceso.ChangePassword;

/**
 * 
 * @author Ramon Sobrino Llorca
 *
 */
public interface UpdateInfo{

	public boolean UpdateCitizen(Ciudadano ciudadano);
	public Ciudadano UpdateCitizen(ChangePassword info);
}
