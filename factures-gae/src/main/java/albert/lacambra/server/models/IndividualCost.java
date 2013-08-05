package albert.lacambra.server.models;

import albert.lacambra.client.models.IndividualCostDTO;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

@EntitySubclass
public class IndividualCost extends Cost<IndividualCost> {

	public static Key<IndividualCost> key(Key<PersistedBudget> parent, Long id) {
		return Key.create(parent, IndividualCost.class, id);
	}

	public IndividualCost(){}
	public IndividualCost(Key<NewPersistedBudget> budget, IndividualCostDTO costDTO) {

		this.budget = budget;

		setConcept(costDTO.getConcept())
		.setCost(costDTO.getCost())
		.setDate(costDTO.getDate())
		.setId(costDTO.getId())
		.setTags(costDTO.getTags());

	}

	public IndividualCost(IndividualCostDTO costDTO) {

		setConcept(costDTO.getConcept())
		.setCost(costDTO.getCost())
		.setDate(costDTO.getDate())
		.setId(costDTO.getId())
		.setTags(costDTO.getTags());

	}

	@Index
	private Long date;

	public Long getDate() {
		return date;
	}

	public IndividualCost setDate(Long date) {
		this.date = date;
		return this;
	}

	public IndividualCostDTO getIndividualCostDTO() {

		return new IndividualCostDTO()
//		.setBudgetId(getBudget().getId())
		.setConcept(getConcept())
		.setCost(getCost())
		.setDate(getDate())
		.setId(getId())
		.setTags(getTags())
		;

	}

	@Override
	public IndividualCostDTO getDTO() {
		return getIndividualCostDTO();
	}
}






















































