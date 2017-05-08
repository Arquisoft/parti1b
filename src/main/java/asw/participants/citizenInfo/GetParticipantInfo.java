package asw.participants.citizenInfo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import asw.participants.citizenInfo.ParticipantsInfo;
import asw.participants.citizenInfo.ParticipantsLogin;

/**
 * Clase que permite al usuario entrar en sesión para comprobar sus datos
 * mediante combinacion email/contraseña
 * @author Pablo
 *
 */
public interface GetParticipantInfo{
	public ResponseEntity<ParticipantsInfo> getInfoParticipant(@RequestBody ParticipantsLogin info);
}
