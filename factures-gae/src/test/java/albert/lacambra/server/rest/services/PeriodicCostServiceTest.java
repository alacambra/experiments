package albert.lacambra.server.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

public class PeriodicCostServiceTest extends BasicTest<PeriodicCostService>{

	@Before
	public void setup() {
		super.setup();
		target = new PeriodicCostService();
	}

	@Test
	public void getPeriodicCost() {
		PeriodicCost cost = getNewPeriodicCost();
		PeriodicCost c2 = target.getPeriodicCost(cost.getBudgetId(), cost.getId());
		assertEquals(cost.getId(), c2.getId());
	}
	
	@Test
	public void getPeriodicCosts() {
		PeriodicCost cost = getNewPeriodicCost();
		List<PeriodicCost> l = target.getPeriodicCosts(2014);
		assertTrue(l.size() == 1);
	}
	
	@Test
	public void savePeriodicCost() {
		PersistedBudget bg = getNewBudget();
		PeriodicCost cost = new PeriodicCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		Long id = target.savePeriodicCost(cost);
		
		assertTrue(id > 0);
	}
	
	@Test
	public void updatePeriodicCost() {
		PeriodicCost cost = getNewPeriodicCost().setConcept("updated");
		target.updatePeriodicCost(cost.getId(), cost);
		cost = target.getPeriodicCost(cost.getBudgetId(), cost.getId());
		assertEquals("updated", cost.getConcept());
	}
	
	@Test
	public void deletePeriodicCost() {
		PeriodicCost cost = getNewPeriodicCost();
		target.deletePeriodicCost(cost.getBudgetId(), cost.getId());
		cost = target.getPeriodicCost(cost.getBudgetId(), cost.getId());
		assertNull(cost);
	}
	
}
























































