package asw.DBManagement.DBManagementParticipants;

import asw.DBManagement.model.CitizenDB;
import asw.participants.citizenInfo.ParticipantsLogin;

/**
 * @author Pablo
 */

public interface GetParticipant {
    public CitizenDB getCiudadano(String email);
    public CitizenDB getCiudadano(ParticipantsLogin participante);
}
