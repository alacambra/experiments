package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PersistedBudget;

public class IndividualCostService extends BasicService implements IIndividualCostService {

	@Override
	public IndividualCost getIndividualCost(Long budgetId, Long costId) {
		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<IndividualCost> key = IndividualCost.key(budgetKey, costId);
		IndividualCost pc = ofy().load().key(key).now();
		return pc;
	}

	@Override
	public List<IndividualCost> getIndividualCosts(Integer year) {
		
		List<IndividualCost> l = 
				ofy().load().type(IndividualCost.class).filter("year =", year).list();
		
		return l;
	}

	@Override
	public Long saveIndividualCost(IndividualCost cost) {
		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		cost.setBudget(budgetKey);
		Long id = ofy().save().entity(cost).now().getId();
		
		return id;
	}

	@Override
	public void updateIndividualCost(Long id, IndividualCost cost) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		ofy().save().entities(cost.setBudget(budgetKey).setId(id));
	}

	@Override
	public void deleteIndividualCost(Long budgetId, Long costId) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<IndividualCost> costKey = IndividualCost.key(budgetKey, costId);
		ofy().delete().key(costKey).now();
	}
}





















































