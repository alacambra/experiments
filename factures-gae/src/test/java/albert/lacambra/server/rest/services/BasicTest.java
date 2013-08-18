package albert.lacambra.server.rest.services;

import org.junit.After;
import org.junit.Before;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostEntry;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public abstract class BasicTest<T> {
	protected static final LocalServiceTestHelper helper = 
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig())
	.setEnvIsLoggedIn(true)
	.setEnvIsAdmin(true)
	.setEnvAuthDomain("something")
	.setEnvEmail("test@test.com");

	protected T target;
	
	@Before
	public void setup() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	protected PersistedBudget getNewBudget() {

		return getNewBudget(0);
	}

	protected PersistedBudget getNewBudget(Integer year) {
		PersistedBudget budget = new PersistedBudget();
		budget.setOwner(Person.key("test@test.com"))
		.setName("bgTest")
		.setYear(year);
		
		BudgetService service = new BudgetService();
		
		Long id = service.addBudget(budget);
		budget.setId(id);
		return budget;

	}

	protected PersistedBudget getBudget(Long id) {
		BudgetService service = new BudgetService();
		return service.getBudget(id);
	}
	
	protected IndividualCost getNewIndividualCost(){
		PersistedBudget bg = getNewBudget();
		
		IndividualCost cost = new IndividualCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		IndividualCostService service = new IndividualCostService();
		Long id = service.saveIndividualCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCost getNewPeriodicCost(){
		PersistedBudget bg = getNewBudget();
		
		PeriodicCost cost = new PeriodicCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		PeriodicCostService service = new PeriodicCostService();
		Long id = service.savePeriodicCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCost getNewPeriodicCost(Long start, Long end){
		PersistedBudget bg = getNewBudget();
		
		PeriodicCost cost = new PeriodicCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		PeriodicCostService service = new PeriodicCostService();
		Long id = service.savePeriodicCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCostEntry getNewPeriodicCostEntry() {
		PeriodicCost periodicCost = getNewPeriodicCost();
		return getNewPeriodicCostEntry(periodicCost);
	}	
	
	protected PeriodicCostEntry getNewPeriodicCostEntry(PeriodicCost periodicCost) {
		PeriodicCostService service = new PeriodicCostService();
		PeriodicCostEntry entry = new PeriodicCostEntry(periodicCost.getKey());
		Long id = service.addCostEntry(periodicCost.getBudgetId(), periodicCost.getId(), entry);
		entry.setId(id);
		return entry;
	}	
}






















































