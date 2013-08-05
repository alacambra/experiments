/*
 */

package albert.lacambra.server.ofy;

import albert.lacambra.server.models.Cost;
import albert.lacambra.server.models.NewPersistedBudget;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.PersistedInvoice;
import albert.lacambra.server.models.Person;
import albert.lacambra.server.models.IndividualCost;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Gives us our custom version rather than the standard Objectify one.  Also responsible for setting up the static
 * OfyFactory instead of the standard ObjectifyFactory.
 * 
 * @author Jeff Schnitzer
 */
public class OfyService
{
	static {
		factory().register(PersistedInvoice.class);
		factory().register(PersistedBudget.class);
		factory().register(Person.class);
		factory().register(Cost.class);
		factory().register(IndividualCost.class);
		factory().register(PeriodicCost.class);
		factory().register(NewPersistedBudget.class);
	}   

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
