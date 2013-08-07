package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.List;

import javax.ws.rs.core.Response;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.NewPersistedBudget;

public class IndividualCostService extends BasicService implements IIndividualCostService {

	@Override
	public IndividualCost getIndividualCost(Long budgetId, Long costId) {
		
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<IndividualCost> key = IndividualCost.key(budgetKey, costId);
		IndividualCost pc = ofy().load().key(key).now();
		pc = new IndividualCost();
		pc.setConcept("lalalala");
		return pc;
	}

	@Override
	public List<IndividualCost> getIndividualCosts(Integer year) {
		
		List<IndividualCost> l = 
				ofy().load().type(IndividualCost.class).filter("year =", year).list();
		
		return l;
	}

	@Override
	public Response saveIndividualCost(IndividualCost cost) {
		
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		cost.setBudget(budgetKey);
		Long id = ofy().save().entity(cost).now().getId();
		
		return Response.ok().header("X-insertedId", id).build();
	}

	@Override
	public Response updateIndividualCost(Long id, IndividualCost cost) {
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		ofy().save().entities(cost.setBudget(budgetKey).setId(id));
		return null;
	}

	@Override
	public void deleteIndividualCost(Long budgetId, Long costId) {
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<IndividualCost> costKey = IndividualCost.key(budgetKey, costId);
		ofy().delete().key(costKey);
	}
}





















































