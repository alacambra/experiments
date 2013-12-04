package albert.lacambra.server.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.Person;
import albert.lacambra.server.models.legacy.PersistedBudgetLegacy;
import albert.lacambra.server.models.legacy.PersistedInvoice;
import static albert.lacambra.server.ofy.OfyService.ofy;

public class VersionConverter {

	public void convertAll() {
		List<PersistedBudgetLegacy> l = ofy().load().type(PersistedBudgetLegacy.class)
				.ancestor(Person.key("alacambra@gmail.com")).list();
		
		for(PersistedBudgetLegacy legacy : l) {
			
			PersistedBudget budget = new PersistedBudget();
			budget.setAmount(
					legacy.getAssignation()).
					setName(legacy.getName()).
					setYear(2013).setOwner(Person.key("alacambra@gmail.com"));
			
			Key<PersistedBudget>  budgetKey = ofy().save().entity(budget).now();
			List<PersistedInvoice> invoices = getInvoices(legacy.getId());
			
			for (PersistedInvoice i : invoices) {
				IndividualCost cost = new IndividualCost();
				cost.setBudget(budgetKey).setTags(i.getExtra()).setCost(i.getPrice()).setDate(i.getDate());
				ofy().save().entity(cost);
			}
		}
	}

	public List<PersistedInvoice> getInvoices(Long id){

		List<PersistedInvoice> l = 
				ofy()
				.load()
				.type(PersistedInvoice.class)
				.ancestor(PersistedBudgetLegacy.key(Person.key("alacambra@gmail.com"), id))
				.list();

		return l;
		
	}
}
