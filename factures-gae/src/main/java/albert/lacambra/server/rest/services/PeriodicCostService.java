package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.List;

import javax.ws.rs.core.Response;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.NewPersistedBudget;
import albert.lacambra.server.models.PeriodicCost;

public class PeriodicCostService extends BasicService implements IPeriodicCostService {

	@Override
	public PeriodicCost getPeriodicCost(Long budgetId, Long costId) {
		
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> key = PeriodicCost.key(budgetKey, costId);
		PeriodicCost pc = ofy().load().key(key).now();
		pc = new PeriodicCost();
		pc.setConcept("lalalala");
		return pc;
	}

	@Override
	public List<PeriodicCost> getPeriodicCosts(Integer year) {
		
		List<PeriodicCost> l = 
				ofy().load().type(PeriodicCost.class).filter("year =", year).list();
		
		l.add(new PeriodicCost().setConcept("my new concept"));
		
		return l;
	}

	@Override
	public Response savePeriodicCost(PeriodicCost cost) {
		
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		cost.setBudget(budgetKey);
		Long id = ofy().save().entity(cost).now().getId();
		
		return Response.ok().header("X-insertedId", id).build();
	}

	@Override
	public Response updatePeriodicCost(Long id, PeriodicCost cost) {
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		ofy().save().entities(cost.setBudget(budgetKey).setId(id));
		return null;
	}

	@Override
	public void deletePeriodicCost(Long budgetId, Long costId) {
		Key<NewPersistedBudget> budgetKey = NewPersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> costKey = PeriodicCost.key(budgetKey, costId);
		ofy().delete().key(costKey);
	}

	@Override
	public IndividualCost getIndividualCost(Long budgetId, Long invoiceId) {
		IndividualCost cost = new IndividualCost();
		cost.setConcept("lololo");
		return cost;
	}

	@Override
	public Response savePeriodicCost(IndividualCost cost) {
		// TODO Auto-generated method stub
		return null;
	}

}
