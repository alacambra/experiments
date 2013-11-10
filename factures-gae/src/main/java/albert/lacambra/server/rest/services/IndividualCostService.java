package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;

import albert.lacambra.server.models.Cost;
import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PersistedBudget;

public class IndividualCostService extends BasicService implements IIndividualCostService {

	@Inject IBudgetService budgetService;
	
	
	@Override
	public IndividualCost getIndividualCost(Long budgetId, Long costId) {
		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<IndividualCost> key = IndividualCost.key(budgetKey, costId);
		IndividualCost pc = ofy().load().key(key).now();
		return pc;
	}

	@Override
	public List<IndividualCost> getIndividualCosts(Integer year) {
		
		List<PersistedBudget> budgets = budgetService.getBudgetsForYear(year);
		List<IndividualCost> l = new ArrayList<IndividualCost>();
		
		for (PersistedBudget budget:budgets) {
//			Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
			l.addAll(ofy().load().type(IndividualCost.class).filterKey(bracelet.getMeKey()).list());
//							bracelet.getMeKey(), PersistedBudget.key(bracelet.getMeKey(), budget.getId())));
		}
		
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





















































