package albert.lacambra.server.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;

@Singleton
public class AuthFilter extends AbstractFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("in filter");
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if ( user == null ) {
			
			String url = userService.createLoginURL("/factures-gae.html");
			response.setStatus(Status.UNAUTHORIZED.getStatusCode());
			response.getWriter().print(url);
			
			
		} 
		else {
//
//			Key<Person> key = Person.key(user.getEmail());
//			Person p = ofy().load().key(key).getValue();
//			
//			if ( p == null ) {
//				p = new Person(user.getEmail(), user.getNickname());
//				key = ofy().save().entity(p).now();
//			}
//			
//			request.setAttribute("key", key);
			chain.doFilter(request, response);
		}
		
	}

}
