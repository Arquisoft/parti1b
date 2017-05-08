package asw.DBManagement.DBManagementParticipants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.DBManagement.DBManagementParticipants.UpdateInfo;
import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.repository.CitizenDBRepository;
import asw.participants.changeInfo.ChangePassword;


@Component
public class UpdateInfoDB implements UpdateInfo {


	@Autowired
	private CitizenDBRepository repositorio;

	@Override
	public boolean UpdateCitizen(CitizenDB ciudadano) {
		CitizenDB citizen= repositorio.save(ciudadano);
		return citizen.equals(ciudadano);
	}

	@Override
	public CitizenDB UpdateCitizen(ChangePassword info) {

		CitizenDB citizen = repositorio.findByMail(info.getEmail());
		if(citizen==null)return null;
		if(!citizen.getPassword().equals(info.getPassword())) return null;

		citizen.setPassword(info.getNewPassword());
		repositorio.save(citizen);
		return citizen;
	}

	
}