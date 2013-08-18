package albert.lacambra.server.guice;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.rest.services.BudgetService;
import albert.lacambra.server.rest.services.IndividualCostService;
import albert.lacambra.server.rest.services.PeriodicCostService;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;

public class GuiceModule extends AbstractModule {
	
		  @Override 
		  protected void configure() {

		    bind(Bracelet.class).in(RequestScoped.class);
		    bind(PeriodicCostService.class).in(RequestScoped.class);
		    bind(IndividualCostService.class).in(RequestScoped.class);
		    bind(BudgetService.class).in(RequestScoped.class);
		}
}
