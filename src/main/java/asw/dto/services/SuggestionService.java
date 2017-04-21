package asw.dto.services;

import java.util.List;

import asw.dto.model.CitizenDB;
import asw.dto.model.Suggestion;

public interface SuggestionService {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	Suggestion findByTitle(String title);
	Suggestion findById(Long id);
	List<Suggestion> findAll();
	
    Suggestion createSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
}
