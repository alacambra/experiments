package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.PeriodicCost;

public class PeriodicCostService extends BasicService implements IPeriodicCostService {

	@Override
	public PeriodicCost getPeriodicCost(Long budgetId, Long costId) {
		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> key = PeriodicCost.key(budgetKey, costId);
		PeriodicCost pc = ofy().load().key(key).now();
		return pc;
	}

	@Override
	public List<PeriodicCost> getPeriodicCosts(Integer year) {
		
		List<PeriodicCost> l = 
				ofy().load().type(PeriodicCost.class).filter("year =", year).list();
		
		return l;
	}

	@Override
	public Long savePeriodicCost(PeriodicCost cost) {
		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		cost.setBudget(budgetKey);
		Long id = ofy().save().entity(cost).now().getId();
		
		return id;
	}

	@Override
	public void updatePeriodicCost(Long id, PeriodicCost cost) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		ofy().save().entities(cost.setBudget(budgetKey).setId(id));
	}

	@Override
	public void deletePeriodicCost(Long budgetId, Long costId) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> costKey = PeriodicCost.key(budgetKey, costId);
		ofy().delete().key(costKey);
	}
}




























































