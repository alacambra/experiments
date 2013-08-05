package albert.lacambra.client.models;

import albert.lacambra.server.models.PeriodStep;

public class PeriodicCostDTO extends CostDTO<PeriodicCostDTO>{

	private PeriodStep periodStep;
	private Long start;
	private Long end;
	private Boolean isFixedCost;
	private Integer foreseenCost;
	private PeriodStep step = PeriodStep.MONTH;
	
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
	public PeriodicCostDTO setPeriodStep(PeriodStep periodStep) {
		this.periodStep = periodStep;
		return this;
	}
	public PeriodicCostDTO setStart(Long start) {
		this.start = start;
		return this;
	}
	public PeriodicCostDTO setEnd(Long end) {
		this.end = end;
		return this;
	}
	public PeriodicCostDTO setIsFixedCost(Boolean isFixedCost) {
		this.isFixedCost = isFixedCost;
		return this;
	}
	public PeriodicCostDTO setForeseenCost(Integer foreseenCost) {
		this.foreseenCost = foreseenCost;
		return this;
	}
}






















































