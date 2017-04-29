package asw.dto.DBManagement;

import asw.dto.DBManagement.model.Ciudadano;

/**
 * 
 * @author Ramon Sobrino Llorca
 *
 */
public interface UpdateInfo{

	public boolean UpdateCitizen(Ciudadano ciudadano);
	public Ciudadano UpdateCitizen(ChangePassword info);
}
