package albert.lacambra.server.models;

import albert.lacambra.client.models.PeriodicCostDTO;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

@EntitySubclass
public class PeriodicCost extends Cost<PeriodicCost> {

	@Index private PeriodStep periodStep;
	@Index private Boolean isFixedCost;
	
	private Long start;
	private Long end;
	private Integer foreseenCost;

	public static Key<PeriodicCost> key(Key<NewPersistedBudget> parent, Long id) {
		return Key.create(parent, PeriodicCost.class, id);
	}

	public PeriodicCost(){}
	public PeriodicCost(Key<NewPersistedBudget> budget, PeriodicCostDTO costDTO){

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

	public Boolean getIsFixedCost() {
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

}






















































