package asw.DBManagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;
import asw.DBManagement.repository.VoteSuggestionRepository;
import asw.DBManagement.services.VoteSuggestionService;

public class VoteSuggestionServiceImpl implements VoteSuggestionService {

	@Autowired
	private VoteSuggestionRepository voteSuggestionServiceImpl;
	
	@Override
	public List<VoteSuggestion> findBySuggestion(Suggestion suggestion) {
		return voteSuggestionServiceImpl.findBySuggestionEquals(suggestion);
	}

	@Override
	public List<VoteSuggestion> findByCitizenDB(CitizenDB citizen) {
		return voteSuggestionServiceImpl.findByCitizenDB(citizen);
	}

	//@Override 
	//public VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey) {
	//	return voteSuggestionServiceImpl.findByVoteCommentKey(voteSuggestionKey);
	//}

	@Override
	public VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion) {
		return voteSuggestionServiceImpl.save(voteSuggestion);
	}

	@Override
	public void deleteVoteSuggestion(VoteSuggestion voteSuggestion) {
		voteSuggestionServiceImpl.delete(voteSuggestion);
		
	}
}
