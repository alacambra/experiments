package albert.lacambra.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import lombok.NoArgsConstructor;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.Invoice;
import albert.lacambra.server.models.Person;
import static albert.lacambra.server.ofy.OfyService.ofy;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;

@Path("/")
@NoArgsConstructor
public class TestResource {
	
	Bracelet bracelet;
	ObjectMapper m = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Inject
	public TestResource(@Context HttpServletRequest request, Bracelet bracelet) {
//		org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener
		this.bracelet = bracelet;
		this.bracelet.login((Key<Person>) request.getAttribute("key"));
		
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
