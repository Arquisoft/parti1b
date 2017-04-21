package asw.dto.DBManagement_original;

import asw.dto.DBManagement_original.model.Ciudadano;
import asw.participants.acceso.ParticipantsLogin;

/**
 * 
 * @author Pablo
 *
 */

public interface GetParticipant{
	public Ciudadano getCiudadano(String email);
	public Ciudadano getCiudadano(ParticipantsLogin participante);
}
