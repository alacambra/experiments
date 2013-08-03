package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class PeriodicCost extends Cost {

	private PeriodStep periodStep;
	private Long start;
	private Long end;
	private Boolean isFixedCost;
	private Integer foreseenCost;
	private PeriodStep step = PeriodStep.MONTH;
	
	public static Key<PeriodicCost> key(Key<PersistedBudget> parent, Long id) {
		return Key.create(parent, PeriodicCost.class, id);
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
	
	public PeriodStep getStep() {
		return step;
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
	
	public PeriodicCost setStep(PeriodStep step) {
		this.step = step;
		return this;
	}

}
