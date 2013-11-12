package albert.lacambra.server.rest.services;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.rest.services.BudgetService;
import albert.lacambra.server.rest.services.IBudgetService;
import albert.lacambra.server.rest.services.IIndividualCostService;
import albert.lacambra.server.rest.services.IPeriodicCostService;
import albert.lacambra.server.rest.services.IndividualCostService;
import albert.lacambra.server.rest.services.PeriodicCostService;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class GuiceModule extends AbstractModule {
	
		  @Override 
		  protected void configure() {

		    bind(Bracelet.class).in(Singleton.class);
		    bind(IPeriodicCostService.class).to(PeriodicCostService.class).in(Singleton.class);
		    bind(IIndividualCostService.class).to(IndividualCostService.class).in(Singleton.class);
		    bind(IBudgetService.class).to(BudgetService.class).in(Singleton.class);
		}
}
