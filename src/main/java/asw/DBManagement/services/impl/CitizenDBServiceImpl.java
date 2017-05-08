package asw.DBManagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.repository.CitizenDBRepository;
import asw.DBManagement.repository.CommentRepository;
import asw.DBManagement.repository.SuggestionRepository;
import asw.DBManagement.services.CitizenDBService;

@Service
public class CitizenDBServiceImpl implements CitizenDBService {
	
	@Autowired
	private CitizenDBRepository citizenDBRepository;
	@SuppressWarnings("unused")
	private SuggestionRepository suggestionRepository;
	@SuppressWarnings("unused")
	private CommentRepository commentRepository;

	@Autowired
	public void setCitizenDBRepository(CitizenDBRepository citizenDBRepository){
		this.citizenDBRepository = citizenDBRepository;
	}

	@Autowired
	public void setSuggestionRepository(SuggestionRepository suggestionRepository){
		this.suggestionRepository = suggestionRepository;
	}

	@Autowired
	public void setCommentRepository(CommentRepository commentRepository){
		this.commentRepository = commentRepository;
	}

	@Override
	public CitizenDB findByMail(String email) {
		return citizenDBRepository.findByMail(email);
	}

	@Override
	public CitizenDB createCitizenDB(CitizenDB citizenDB) {
		CitizenDB citizen = this.citizenDBRepository.save(citizenDB);
		return citizen;
	}

}
