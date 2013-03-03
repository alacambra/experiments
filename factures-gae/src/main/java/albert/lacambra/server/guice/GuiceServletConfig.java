package albert.lacambra.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new GuiceModule(), new ServletModule(){

			@Override
			protected void configureServlets() {
				serve("/rest/*").with(GuiceContainer.class);
//				serve("/rest/*").with(org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher.class);
			}
		});	
	}
}
