package asw.dto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dto.model.CitizenDB;
import asw.dto.model.Suggestion;
import asw.dto.model.VoteSuggestion;
import asw.dto.model.key.VoteSuggestionKey;
import asw.dto.repository.VoteSuggestionRepository;
import asw.dto.services.VoteSuggestionService;

public class VoteSuggestionServiceImpl implements VoteSuggestionService {

	@Autowired
	private VoteSuggestionRepository voteSuggestionServiceImpl;
	
	@Override
	public List<VoteSuggestion> findBySuggestion(Suggestion suggestion) {
		return voteSuggestionServiceImpl.findBySuggestion(suggestion);
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
