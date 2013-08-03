package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class VariableCost extends Cost {
	
	public static Key<VariableCost> key(Key<PersistedBudget> parent, Long id) {
		return Key.create(parent, VariableCost.class, id);
	}
	
	private Long date;

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}


}
