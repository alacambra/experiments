package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.Cost;
import albert.lacambra.server.models.PeriodicCostEntry;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.PeriodicCost;

public class PeriodicCostService extends BasicService implements IPeriodicCostService {

	@Inject
	public PeriodicCostService(Bracelet bracelet) {
		super(bracelet);
	}

	@Override
	public PeriodicCost getPeriodicCost(Long budgetId, Long costId) {

		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> key = PeriodicCost.key(budgetKey, costId);
		PeriodicCost pc = ofy().load().key(key).now();
		
		if ( pc == null )
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).build());
		
		return pc;
	}

	@Override
	public List<PeriodicCost> getPeriodicCosts(Integer year) {

		List<Cost> l = 
				ofy().load().type(Cost.class).filter("year =", year).list();
		
		List<PeriodicCost> periodicCosts = new ArrayList<PeriodicCost>();

		for ( Cost c : l ){
			if ( c instanceof PeriodicCost)
				periodicCosts.add((PeriodicCost) c);
		}
		
		return periodicCosts;
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
		Long start = cost.getStart();
		Long end = cost.getEnd();
		
		if ( end < start) {
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
					.entity("Start date can not be bigger than end date")
					.build());
		}

		Calendar calenderStart = Calendar.getInstance();
		calenderStart.setTime(new Date(start));
		int startYear = calenderStart.get(Calendar.YEAR);
		int endYear = startYear;
		
		if ( end != null ||  end > 0) {
			Calendar calenderEnd = Calendar.getInstance();
			calenderEnd.setTime(new Date(end));
			endYear = calenderEnd.get(Calendar.YEAR);
		}
		
//		do{
			Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
			ofy().save().entity(cost.setBudget(budgetKey).setId(id)).now();
//		}while(startYear != endYear);
			
	}

	@Override
	public void deletePeriodicCost(Long budgetId, Long costId) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PeriodicCost> costKey = PeriodicCost.key(budgetKey, costId);
		ofy().delete().key(costKey).now();
	}

	@Override
	public Long addCostEntry(Long budgetId, Long costId, PeriodicCostEntry entry) {
		entry.setCostKey(PeriodicCost.key(PersistedBudget.key(bracelet.getMeKey(), budgetId), costId));
		Long id = ofy().save().entity(entry).now().getId();
		return id;
	}

	@Override
	public void updateCostEntry(Long budgetId, Long costId, Long entryId, PeriodicCostEntry entry) {
		entry.setCostKey(PeriodicCost.key(PersistedBudget.key(bracelet.getMeKey(), budgetId), costId));
		entry.setId(entryId);
		ofy().save().entity(entry).now();
	}

	@Override
	public void deleteCostEntry(Long budgetId, Long costId, Long entryId) {
		
		ofy().delete().key(
				PeriodicCostEntry.key(
						PeriodicCost.key(
								PersistedBudget.key(
										bracelet.getMeKey(), budgetId), costId), entryId)).now();
	}
}

























































