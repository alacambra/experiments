package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class PeriodicCost extends Cost {

	private PeriodStep periodStep;
	private Long start;
	private Long end;
	private Boolean isFixedCost;
	
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
	public void setPeriodStep(PeriodStep periodStep) {
		this.periodStep = periodStep;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public void setIsFixedCost(Boolean isFixedCost) {
		this.isFixedCost = isFixedCost;
	}

}
