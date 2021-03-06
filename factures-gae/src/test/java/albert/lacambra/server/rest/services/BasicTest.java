package albert.lacambra.server.rest.services;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostEntry;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class BasicTest<T> {
	
	protected Injector injector = Guice.createInjector(new GuiceModule());
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
		
		BudgetService service = injector.getInstance(BudgetService.class);
		
		Long id = service.addBudget(budget);
		budget.setId(id);
		return budget;

	}

	protected PersistedBudget getBudget(Long id) {
		BudgetService service = injector.getInstance(BudgetService.class);
		return service.getBudget(id);
	}
	
	protected IndividualCost getNewIndividualCost(){
		PersistedBudget bg = getNewBudget();
		return getNewIndividualCost(bg);
	}
	
	protected IndividualCost getNewIndividualCost(int year){
		PersistedBudget bg = getNewBudget(year);
		return getNewIndividualCost(bg);
	}
	
	private IndividualCost getNewIndividualCost(PersistedBudget bg) {
		IndividualCost cost = new IndividualCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		IndividualCostService service = injector.getInstance(IndividualCostService.class);
		Long id = service.saveIndividualCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCost getNewPeriodicCost(){
		PersistedBudget bg = getNewBudget(2014);
		
		PeriodicCost cost = new PeriodicCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setStart(new Date().getTime()).setEnd(new Date().getTime() + 10)
		.setBudgetId(bg.getId());
		
		PeriodicCostService service = injector.getInstance(PeriodicCostService.class);
		Long id = service.savePeriodicCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCost getNewPeriodicCost(Long start, Long end){
		PersistedBudget bg = getNewBudget();
		
		PeriodicCost cost = new PeriodicCost()
		.setBudget(PersistedBudget.key(Person.key("test@test.com"), bg.getId()))
		.setBudgetId(bg.getId());
		
		PeriodicCostService service = injector.getInstance(PeriodicCostService.class);
		Long id = service.savePeriodicCost(cost);
		cost.setId(id);
		return cost;
	}
	
	protected PeriodicCostEntry getNewPeriodicCostEntry() {
		PeriodicCost periodicCost = getNewPeriodicCost();
		return getNewPeriodicCostEntry(periodicCost);
	}	
	
	protected PeriodicCostEntry getNewPeriodicCostEntry(PeriodicCost periodicCost) {
		PeriodicCostService service = injector.getInstance(PeriodicCostService.class);
		PeriodicCostEntry entry = new PeriodicCostEntry(periodicCost.getKey());
		Long id = service.addCostEntry(periodicCost.getBudgetId(), periodicCost.getId(), entry);
		entry.setId(id);
		return entry;
	}	
}






















































