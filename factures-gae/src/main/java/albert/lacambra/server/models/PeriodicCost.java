package albert.lacambra.server.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

import static albert.lacambra.server.ofy.OfyService.ofy;

@EntitySubclass(index=true)
public class PeriodicCost extends Cost<PeriodicCost> {

	@Index private PeriodStep periodStep;
	@Index private Boolean isFixedCost;
	
	private Long start;
	private Long end;
	
	/*
	 * For Non fixed periodic cost, how much is expected the amount to be 
	 */
	private Integer foreseenCost;

	public static Key<PeriodicCost> key(Key<PersistedBudget> parent, Long id) {
		return Key.create(parent, PeriodicCost.class, id);
	}

	public PeriodicCost(){}
	public PeriodicCost(Key<PersistedBudget> budget, PeriodicCostDTO costDTO){

		this(costDTO);
		this.budget = budget;

	}
	
	public PeriodicCost(PeriodicCostDTO costDTO){

		setConcept(costDTO.getConcept())
		.setCost(costDTO.getCost())
		.setEnd(costDTO.getEnd())
		.setForeseenCost(costDTO.getForeseenCost())
		.setId(costDTO.getId())
		.setIsFixedCost(costDTO.getIsFixedCost())
		.setPeriodStep(costDTO.getPeriodStep())
		.setStart(costDTO.getStart())
		.setBudgetId(costDTO.getBudgetId())
		.setTags(costDTO.getTags());
		
	}

	public PeriodStep getPeriodStep() {
		return periodStep;
	}

	public Long getStart() {
		return start;
	}

	public Long getEnd() {
		return end;
	}

	public Boolean isFixedCost() {
		return isFixedCost;
	}

	public Integer getForeseenCost() {
		return foreseenCost;
	}

	public PeriodicCost setPeriodStep(PeriodStep periodStep) {
		this.periodStep = periodStep;
		return this;
	}

	public PeriodicCost setStart(Long start) {
		this.start = start;
		return this;
	}

	public PeriodicCost setEnd(Long end) {
		this.end = end;
		return this;
	}

	public PeriodicCost setIsFixedCost(Boolean isFixedCost) {
		this.isFixedCost = isFixedCost;
		return this;
	}

	public PeriodicCost setForeseenCost(Integer foreseenCost) {
		this.foreseenCost = foreseenCost;
		return this;
	}
	
	public Set<PeriodicCostEntry> getCostEntries() {
		
		if ( isFixedCost ) return null;
		
		Set<PeriodicCostEntry> costEntries = new HashSet<PeriodicCostEntry>();
		
		List<Object> entries = ofy().load().ancestor(this).list();
		
		for (Object entry : entries ) {
			if ( entry instanceof PeriodicCostEntry ) {
				costEntries.add((PeriodicCostEntry) entry);
			}
		}
		
		return costEntries;
	}
	
	public PeriodicCostDTO getPeriodicCostDTO() {

		return new PeriodicCostDTO()
		.setBudgetId(getBudget().getId())
		.setConcept(getConcept())
		.setCost(getCost())
		.setStart(getStart())
		.setEnd(getEnd())
		.setForeseenCost(foreseenCost)
		.setIsFixedCost(isFixedCost)
		.setPeriodStep(periodStep)
		.setId(getId())
		.setTags(getTags());
	}

	@SuppressWarnings("unchecked")
	@Override
	public PeriodicCostDTO getDTO() {
		return getPeriodicCostDTO();
	}

	public Key<PeriodicCost> getKey() {
		return PeriodicCost.key(budget, id);
	}
}






















































