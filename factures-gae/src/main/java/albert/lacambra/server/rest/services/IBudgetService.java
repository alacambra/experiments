package albert.lacambra.server.rest.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.client.models.DTOBudget;

public interface IBudgetService {

	public abstract DTOBudget getBudget(Long id);

	public abstract List<DTOBudget> getBudgetsForYear(Integer year)
			throws JsonGenerationException, JsonMappingException, IOException;

	public abstract Response update();

	public abstract List<DTOBudget> getAllBudgets()
			throws JsonGenerationException, JsonMappingException, IOException;

	public abstract Response saveBudget(DTOBudget dto);

	public abstract Response updateBudget(Long id, DTOBudget dto);

	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	public abstract Response addBudget(DTOBudget dto);

	public abstract List<DTOBudget> getBudgetsForYear(String year)
			throws JsonGenerationException, JsonMappingException, IOException;

}