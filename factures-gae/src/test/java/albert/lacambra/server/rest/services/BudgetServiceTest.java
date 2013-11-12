package albert.lacambra.server.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

public class BudgetServiceTest extends BasicTest<BudgetService>{

	@Before
	public void setup() {
		super.setup();
		target = injector.getInstance(BudgetService.class);
	}

	@Test
	public void  getBudget(){
		PersistedBudget bg1 = getNewBudget();
		PersistedBudget bg2 = getNewBudget();

		PersistedBudget r1 = target.getBudget(bg1.getId());
		PersistedBudget r2 = target.getBudget(bg2.getId());
		assertEquals(bg1.getId(), r1.getId());
		assertEquals(bg2.getId(), r2.getId());
	}

	@Test
	public void  getBudgetsForYear(){
		getNewBudget();
		getNewBudget();
		getNewBudget(2014);
		List<PersistedBudget> budgets = target.getBudgetsForYear(2014);
		assertEquals(1, budgets.size());
	}

	@Test
	public void  getAllBudgets(){
		getNewBudget();
		getNewBudget();
		getNewBudget();
		List<PersistedBudget> budgets = target.getAllBudgets();
		assertEquals(3, budgets.size());
	}

	@Test
	public void  updateBudget(){
		PersistedBudget budget = getNewBudget();
		budget.setName("updated");
		target.updateBudget(budget.getId(), budget);
		budget = getBudget(budget.getId());
		assertEquals("updated", budget.getName());
	}

	@Test
	public void addBudget(){

		PersistedBudget budget = new PersistedBudget();
		budget.setOwner(Person.key("test@test.com"));
		budget.setName("bgTest");
		Long id = target.addBudget(budget);
		assertTrue(id > 0);

	}

}
























































