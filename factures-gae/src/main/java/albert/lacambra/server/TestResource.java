package albert.lacambra.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.Invoice;
import albert.lacambra.server.models.Person;
import static albert.lacambra.server.ofy.OfyService.ofy;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;

@Path("/")
public class TestResource {
	
	Bracelet bracelet;
	Logger log = Logger.getLogger(TestResource.class.getName());
	ObjectMapper m = new ObjectMapper();
	
	public TestResource() {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Key<Person> key = Person.key(user.getEmail());
		Person p = ofy().load().key(key).getValue();
		
		if ( p == null ) {
			p = new Person(user.getEmail(), user.getNickname());
			key = ofy().save().entity(p).now();
		} 
		
		bracelet = new Bracelet();
		bracelet.login(key);
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String testf() throws JsonGenerationException, JsonMappingException, IOException{
		
		Invoice o = new Invoice(bracelet.getMeKey());
		o.setCategory(new Date().toString());
		ofy().save().entity(o);
		List<Invoice> i = ofy().load().type(Invoice.class).ancestor(bracelet.getMeKey()).list();
		return m.writeValueAsString(i);
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String post(String txt) {
		return "my text: " + txt;
	}
}
