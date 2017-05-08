package asw.DBManagement.services;

import java.util.List;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;

public interface VoteSuggestionService {

	List<VoteSuggestion> findBySuggestion (Suggestion suggestion);
	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);
	
	VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion);
	void deleteVoteSuggestion (VoteSuggestion voteSuggestion);
	VoteSuggestion findByCitizenDBAndSuggestion(CitizenDB citizen, Suggestion suggestion);
}
