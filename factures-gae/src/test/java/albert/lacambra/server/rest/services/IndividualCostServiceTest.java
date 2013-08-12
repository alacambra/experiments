package albert.lacambra.server.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

public class IndividualCostServiceTest extends BasicTest<IndividualCostService>{

	@Before
	public void setup() {
		super.setup();
		target = new IndividualCostService();
	}

//		IndividualCostService service = new IndividualCostService();
//		service.getIndividualCost(budgetId, costId)
	
	@Test
	public void getIndividualCosts(){
	
		IndividualCost cost = getNewIndividualCost();
		IndividualCost c2 = target.getIndividualCost(cost.getBudgetId(), cost.getId());
		assertEquals(cost.getId(), c2.getId());
	}

	@Test
	public void saveIndividualCost(){
		
		PersistedBudget bg = getNewBudget();
		IndividualCost cost = new IndividualCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		Long id = target.saveIndividualCost(cost);
		
		assertTrue(id > 0);
	}

	@Test
	public void updateIndividualCost(){
		IndividualCost cost = getNewIndividualCost().setConcept("updated");
		target.updateIndividualCost(cost.getId(), cost);
		cost = target.getIndividualCost(cost.getBudgetId(), cost.getId());
		assertEquals("updated", cost.getConcept());
	}

	@Test
	public void deleteIndividualCost(){
		IndividualCost cost = getNewIndividualCost();
		target.deleteIndividualCost(cost.getBudgetId(), cost.getId());
		cost = target.getIndividualCost(cost.getBudgetId(), cost.getId());
		assertNull(cost);
	}
}
























































