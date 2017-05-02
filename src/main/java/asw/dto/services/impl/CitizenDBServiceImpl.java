package asw.dto.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dto.model.CitizenDB;
import asw.dto.repository.CitizenDBRepository;
import asw.dto.repository.CommentRepository;
import asw.dto.repository.SuggestionRepository;
import asw.dto.services.CitizenDBService;

public class CitizenDBServiceImpl implements CitizenDBService {
	
	@Autowired
	private CitizenDBRepository citizenDBRepository;
	private SuggestionRepository suggestionRepository;
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
	public CitizenDB getCitizenDB(String email) {
		return citizenDBRepository.findByMail(email);
	}

	@Override
	public CitizenDB createCitizenDB(CitizenDB citizenDB) {
		CitizenDB citizen = this.citizenDBRepository.save(citizenDB);
		return citizen;
	}
	
	@Override
	public CitizenDB getByLogin(String login){
		return citizenDBRepository.findByLogin(login);
	}
}
