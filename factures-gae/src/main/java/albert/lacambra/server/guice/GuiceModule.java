package albert.lacambra.server.guice;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.rest.services.BudgetService;
import albert.lacambra.server.rest.services.IBudgetService;
import albert.lacambra.server.rest.services.IIndividualCostService;
import albert.lacambra.server.rest.services.IPeriodicCostService;
import albert.lacambra.server.rest.services.IndividualCostService;
import albert.lacambra.server.rest.services.PeriodicCostService;
import albert.lacambra.server.serialization.BudgetListReaderWriter;
import albert.lacambra.server.serialization.BudgetReaderWriter;
import albert.lacambra.server.serialization.CostListReaderWriter;
import albert.lacambra.server.serialization.CostReaderWriter;
import albert.lacambra.server.serialization.LongWriter;
import albert.lacambra.server.serialization.PeriodicCostEntryReaderWriter;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(Bracelet.class);
	    bind(IPeriodicCostService.class).to(PeriodicCostService.class);
	    bind(IIndividualCostService.class).to(IndividualCostService.class);
	    bind(IBudgetService.class).to(BudgetService.class);
	    bind(BudgetListReaderWriter.class);
	    bind(BudgetReaderWriter.class);
	    bind(CostListReaderWriter.class);
	    bind(PeriodicCostEntryReaderWriter.class);
	    bind(CostReaderWriter.class);
	    bind(LongWriter.class);
	}
}	 
