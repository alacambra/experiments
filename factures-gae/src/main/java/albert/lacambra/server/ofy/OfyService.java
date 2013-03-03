/*
 */

package albert.lacambra.server.ofy;

import albert.lacambra.server.models.Invoice;
import albert.lacambra.server.models.Person;

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
		factory().register(Invoice.class);
		factory().register(Person.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
