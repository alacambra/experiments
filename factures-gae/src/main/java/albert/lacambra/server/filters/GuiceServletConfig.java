package albert.lacambra.server.filters;

import albert.lacambra.server.InvoiceReaderWriter;
import albert.lacambra.server.TestAction;
import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.rest.services.InvoiceResource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		
		return Guice.createInjector(Stage.DEVELOPMENT, new JerseyServletModule() {

			@Override
			protected void configureServlets() {
//				bind(ResourceExceptionMapper.class).in(Singleton.class);
//				bind(FilterTest.class);
				bind(TestAction.class);
				bind(InvoiceResource.class);
//				bind(Person.class);
				bind(Bracelet.class);
				bind(InvoiceReaderWriter.class).in(Singleton.class);
				this.bindResourcesActions();
				filter("/rest/*").through(AuthFilter.class);
				serve("/rest/*").with(GuiceContainer.class);
			}

			/**
			 * Resources' actions bindings
			 */
			private void bindResourcesActions(){
				
//				bind(NotConfirmedContactAction.class);
//				bind(NoteAction.class);
//				bind(UserAction.class);
//				bind(MessagesAction.class);
//				bind(PopulateAction.class);
//				bind(Logout.class);
			}
		}
//		, new Managers()
		);
	}
}





































































