package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class PeriodicCostEntry {

	@Id 
	private Long id;

	/**
	 * Variable periodic cost where this entry belongs
	 */
	@Parent 
	protected Key<PeriodicCost> costKey;

	/**
	 * How much
	 */
	private Integer cost;
	
	/**
	 * This entry is the "stepNumber" entry of the parent variable periodic cost. 
	 */
	private int stepNumber;

	public static Key<PeriodicCostEntry> key(Key<PeriodicCost> parent, Long id) {
		return Key.create(parent, PeriodicCostEntry.class, id);
	}

	public PeriodicCostEntry(){}
	
	public PeriodicCostEntry(Key<PeriodicCost> costKey) {
		super();
		this.costKey = costKey;
	}

	public Long getId() {
		return id;
	}

	public Key<PeriodicCost> getCostKey() {
		return costKey;
	}

	public Integer getCost() {
		return cost;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public PeriodicCostEntry setId(Long id) {
		this.id = id;
		return this;
	}

	public PeriodicCostEntry setCostKey(Key<PeriodicCost> costKey) {
		this.costKey = costKey;
		return this;
	}

	public PeriodicCostEntry setCost(Integer cost) {
		this.cost = cost;
		return this;
	}

	public PeriodicCostEntry setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
		return this;

	}
}



















































