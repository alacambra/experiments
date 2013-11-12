package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.Person;

public abstract class BasicService {

	protected Bracelet bracelet;
//	protected ObjectMapper m = new ObjectMapper();
	protected Logger log;
	
	public BasicService(Bracelet bracelet) {
		
		log = Logger.getLogger(this.getClass().getName());
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Key<Person> key = Person.key(user.getEmail());
		Person p = ofy().load().key(key).now();
		
		if ( p == null ) {
			p = new Person(user.getEmail(), user.getNickname());
			key = ofy().save().entity(p).now();
		} 
		
		bracelet = new Bracelet();
		bracelet.login(key);
		this.bracelet = bracelet;
	}

}
