package asw.dto.services;

import java.util.List;

import asw.dto.model.CitizenDB;
import asw.dto.model.Suggestion;
import asw.dto.model.VoteSuggestion;

public interface VoteSuggestionService {

	List<VoteSuggestion> findBySuggestion (Suggestion suggestion);
	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);
	//VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);
	
	VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion);
	void deleteVoteSuggestion (VoteSuggestion voteSuggestion);
}
