package albert.lacambra.server.guice;

import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import albert.lacambra.server.TestResource;
import albert.lacambra.server.auth.Bracelet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule(){

			@Override
			protected void configureServlets() {

				bind(Bracelet.class).in(RequestScoped.class);
				bind(TestResource.class);

				Map<String, String> params = new HashMap<String, String>();
				params.put("resteasy.guice.modules", GuiceModule.class.getName());
 
				bind(HttpServletDispatcher.class).in(Singleton.class);				
				serve("/rest/*").with(HttpServletDispatcher.class, params);
			}
		});	
	}
}
