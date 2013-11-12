package albert.lacambra.server.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostEntry;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

public class PeriodicCostServiceTest extends BasicTest<PeriodicCostService>{

	@Before
	public void setup() {
		super.setup();
		target = injector.getInstance(PeriodicCostService.class);
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

		try {
			cost = target.getPeriodicCost(cost.getBudgetId(), cost.getId());
		} catch ( WebApplicationException e ) {
			assertTrue(true);	
			return;
		}
		assertTrue(false);
	}

	@Test
	public void addCostEntry() {
		PeriodicCost periodicCost = getNewPeriodicCost();
		PeriodicCostEntry entry = new PeriodicCostEntry(periodicCost.getKey());
		Long id = target.addCostEntry(periodicCost.getBudgetId(), periodicCost.getId(), entry);
		assertTrue(id > 0);
	}

	@Test
	public void updateCostEntry() {
		PeriodicCost periodicCost = getNewPeriodicCost();
		periodicCost.setIsFixedCost(false);		
		PeriodicCostEntry costEntry = getNewPeriodicCostEntry(periodicCost);
		costEntry.setCost(500);
		target.updateCostEntry(periodicCost.getBudgetId(), periodicCost.getId(), costEntry.getId(), costEntry);
		assertTrue(periodicCost.getCostEntries().iterator().next().getCost() == 500);
	}

	@Test
	public void deleteCostEntry() {

		PeriodicCost periodicCost = getNewPeriodicCost();
		periodicCost.setIsFixedCost(false);
		PeriodicCostEntry costEntry = getNewPeriodicCostEntry(periodicCost);

		assertTrue(periodicCost.getCostEntries().size() == 1);

		target.deleteCostEntry(
				costEntry.getCostKey().getParent().getId(), costEntry.getCostKey().getId(), costEntry.getId());

		assertTrue(periodicCost.getCostEntries().size() == 0);

		periodicCost.setIsFixedCost(true);
		assertNull(periodicCost.getCostEntries());

	}


}
























































