package albert.lacambra.server.rest.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.server.models.PersistedBudget;

public interface IBudgetService {

	PersistedBudget getBudget(Long id);

	List<PersistedBudget> getBudgetsForYear(Integer year);

	List<PersistedBudget> getAllBudgets();

	void updateBudget(Long id, PersistedBudget dto);

	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	Long addBudget(PersistedBudget dto);

	List<PersistedBudget> getBudgetsForYear(String year)
			throws JsonGenerationException, JsonMappingException, IOException;

}