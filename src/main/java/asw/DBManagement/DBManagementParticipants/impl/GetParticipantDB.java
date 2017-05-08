package asw.DBManagement.DBManagementParticipants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.DBManagement.DBManagementParticipants.GetParticipant;
import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.repository.CitizenDBRepository;
import asw.participants.citizenInfo.ParticipantsLogin;

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
