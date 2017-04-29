package asw.dto.DBManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.DBManagement_original.model.CiudadanoTest;
import asw.dto.DBManagement.ChangePassword;
import asw.dto.DBManagement.UpdateInfo;
import asw.dto.DBManagement.model.Ciudadano;
import asw.dto.DBManagement.persistence.CiudadanoRepository;


@Component
public class UpdateInfoDB implements UpdateInfo {


	@Autowired
	private CiudadanoRepository repositorio;

	@Override
	public boolean UpdateCitizen(Ciudadano ciudadano) {
		Ciudadano citizen= repositorio.save(ciudadano);
		return citizen.equals(ciudadano);
	}

	@Override
	public Ciudadano UpdateCitizen(ChangePassword info) {

		Ciudadano citizen = repositorio.findByEmail(info.getEmail());
		if(citizen==null)return null;
		if(!citizen.getPassword().equals(info.getPassword())) return null;

		citizen.setPassword(info.getNewPassword());
		repositorio.save(citizen);
		return citizen;
	}

}