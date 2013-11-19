package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.WebApplicationException;
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

	@Inject IBudgetService budgetService;

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
	public List<PeriodicCost> getAllPeriodicCosts(Integer year) {

		List<PersistedBudget> budgets = budgetService.getBudgetsForYear(year);
		List<PeriodicCost> periodicCosts = new ArrayList<PeriodicCost>();

		for (PersistedBudget budget:budgets) {
			List<Cost> allCosts = 
					ofy()
					.load()
					.type(Cost.class)
					.ancestor(budget.key(bracelet.getMeKey(), budget.getId())).list();

			for(Cost<?> cost : allCosts) {
				if (cost instanceof PeriodicCost){
					periodicCosts.add((PeriodicCost) cost);
				}
			}
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

		if ( end != null && end < start) {
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
					.entity("Start date can not be bigger than end date")
					.build());
		}
		
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date(start));
		int startYear = calender.get(Calendar.YEAR);
		
		if ( end == null) {
			cost.setEnd(getDefaultTime(startYear));
		}else{
			
			Calendar calenderEnd = Calendar.getInstance();
			calenderEnd.setTime(new Date(end));

			int endYear = calender.get(Calendar.YEAR);
			if (endYear != startYear) {
				throw new WebApplicationException(
						Response.status(Status.BAD_REQUEST)
						.entity("Start date and end date must belong to the same year")
						.build());
			}
		} 

		
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), cost.getBudgetId());
		ofy().save().entity(cost.setBudget(budgetKey).setId(id)).now();

	}
	
	private long getDefaultTime(int year) {
		try {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateTimeFormat.parse("31/12/" + year).getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
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

























































