package albert.lacambra.server.guice;

import albert.lacambra.server.TestResource;
import albert.lacambra.server.auth.Bracelet;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;

public class GuiceModule extends AbstractModule {
	
		  @Override 
		  protected void configure() {

		    bind(Bracelet.class).in(RequestScoped.class);
		    bind(TestResource.class);

		}
}
