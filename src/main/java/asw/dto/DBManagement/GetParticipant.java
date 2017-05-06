package asw.dto.DBManagement;

import asw.dto.model.CitizenDB;
import asw.participants.acceso.ParticipantsLogin;

/**
 * @author Pablo
 */

public interface GetParticipant {
    public CitizenDB getCiudadano(String email);
    public CitizenDB getCiudadano(ParticipantsLogin participante);
}
