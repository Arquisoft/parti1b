package asw.dto.DBManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.dto.DBManagement.UpdateInfo;
import asw.dto.model.CitizenDB;
import asw.dto.repository.CitizenDBRepository;
import asw.participants.acceso.ChangePassword;


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