package albert.lacambra.server.rest.services;

import static org.junit.Assert.*;
import static albert.lacambra.server.ofy.OfyService.ofy;


import org.junit.BeforeClass;
import org.junit.Test;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

import albert.lacambra.server.models.Invoice;
import albert.lacambra.server.models.Person;


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
	    fact.register(Invoice.class);
	}
	
	@Test 
	public void type() throws Exception {
		assertNotNull(InvoiceService.class);
	}

	private void doTest() {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        assertEquals(0, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
        ds.put(new Entity("yam"));
        ds.put(new Entity("yam"));
        assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
    }
	
	@Test 
	public void instantiation() throws Exception {
		IInvoiceService target = new InvoiceService();
		assertNotNull(target);
	}

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




































