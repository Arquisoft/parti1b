package asw.DBManagement.services;

import java.util.List;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;

public interface SuggestionService {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	Suggestion findByTitle(String title);
	Suggestion findById(Long id);
	List<Suggestion> findAll();
	
    Suggestion createSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
	void update(Suggestion suggestion);
}
