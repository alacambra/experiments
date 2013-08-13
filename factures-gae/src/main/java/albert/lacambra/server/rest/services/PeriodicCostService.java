package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.PeriodicCostEntry;
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
		
		for ( PeriodicCost cost : l ) {
			if ( cost.isFixedCost()) continue;

			Set<PeriodicCostEntry> costEntries = new HashSet<PeriodicCostEntry>();
			
 			List<Object> entries = 
					ofy().load().ancestor(
					PeriodicCost.key(cost.getBudget(), cost.getId())).list();
			
 			for ( Object costEntry : entries ) {
 				if ( costEntry instanceof PeriodicCostEntry ) {
 					costEntries.add((PeriodicCostEntry) costEntries);
 				}
 			}
 			
 			cost.setCostEntries(costEntries);
		}

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
		ofy().delete().key(costKey);
	}

	@Override
	public Long addCostEntry(Long budgetId, Long costId, PeriodicCostEntry entry) {
		entry.setCostKey(PeriodicCost.key(PersistedBudget.key(bracelet.getMeKey(), budgetId), costId));
		Long id = ofy().save().entity(entry).now().getId();
		return id;
	}

	@Override
	public Long updateCostEntry(Long budgetId, Long costId, Long entryId,
			PeriodicCostEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteCostEntry(Long budgetId, Long costId, Long entryId,
			PeriodicCostEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}
}




























































