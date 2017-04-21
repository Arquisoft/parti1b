package asw.dto.DBManagement_original;

import asw.dto.DBManagement_original.model.Ciudadano;
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
