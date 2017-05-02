package asw.dto.DBManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.dto.DBManagement.GetParticipant;
import asw.dto.model.CitizenDB;
import asw.dto.repository.CitizenDBRepository;
import asw.participants.acceso.ParticipantsLogin;

@Component
public class GetParticipantDB implements GetParticipant{

	@Autowired
	private CitizenDBRepository repositorio; 
	
	@Override
	public CitizenDB getCiudadano(String mail) {
		CitizenDB citizen = repositorio.findByMail(mail);
		return citizen;
	}

	@Override
	public CitizenDB getCiudadano(ParticipantsLogin participante) {
		CitizenDB citizen = repositorio.findByMail(participante.getEmail());
		return citizen;
	}


}
