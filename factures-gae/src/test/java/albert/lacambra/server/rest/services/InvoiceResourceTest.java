package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import albert.lacambra.server.models.*;


public class InvoiceResourceTest {

	private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	protected static ObjectifyFactory fact;

	@BeforeClass
	public static void setup() {
		helper.setUp();
		fact = new ObjectifyFactory() {
			@Override
			public Objectify begin() {
				return super.begin();
			}
		};
		fact.register(PersistedInvoice.class);
		fact.register(PersistedBudget.class);
		fact.register(Cost.class);
		fact.register(IndividualCost.class);
		fact.register(PeriodicCost.class);
	}
	
	@Test
	public void addVaribleCost() {


		PersistedBudget pb = new PersistedBudget().setOwner(Person.key("test@test.com"));

		long id = 0;

		pb.setName("budgettest");
		Key<PersistedBudget> key = ofy().save().entity(pb).now();

		IndividualCost cost = new IndividualCost();
		cost.setBudget(key);
		cost.setConcept("concept");
		cost.setCost(123);
		Key<IndividualCost> k = ofy().save().entity(cost).now();
		ofy().clear();
		IndividualCost cost1 = ofy().load().key(k).now();
		
		System.err.println(cost1.toString());
		
	}

	//	@Test 
	//	public void type() throws Exception {
	//		assertNotNull(InvoiceService.class);
	//	}
	//
	//	private void doTest() {
	//        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	//        assertEquals(0, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
	//        ds.put(new Entity("yam"));
	//        ds.put(new Entity("yam"));
	//        assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
	//    }
	//	
	//	@Test 
	//	public void instantiation() throws Exception {
	//		IInvoiceService target = new InvoiceService();
	//		assertNotNull(target);
	//	}

	@Test 
	public void getInvoice_A$() throws Exception {
		//		Invoice i = new Invoice(new Long(1), Person.key("test@example.com"));
		//		ofy().save().entity(i).now();
		//		
		//		InvoiceService target = new InvoiceService();

		//		Invoice actual = target.getInvoice((long) 1);
		////		Invoice expected = i;
		//		assertEquals(expected, actual);
	}

}





































