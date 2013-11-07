package albert.lacambra.server.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;

@Singleton
public class AuthFilter extends AbstractFilter
{
	
	private Logger log;
	
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		super.init(cfg);
		log = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("in filter");
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if ( user == null ) {
			
			String url = userService.createLoginURL("/index.html");
			log.info("redirecting to " + url);
			response.setStatus(Status.UNAUTHORIZED.getStatusCode());
			response.getWriter().print(url);
//			response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
			
		} else {
			
			log.info("Auth user is " + user.getEmail());
			
			try {
				chain.doFilter(request, response);
				log.info("done");
			} catch (Throwable e) {
				log.log(Level.SEVERE, "error: " + e.getMessage(), e);
//				throw new RuntimeException(e);
				throw new WebApplicationException(e);
			} 
		}
	}
}
