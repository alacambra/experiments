package albert.lacambra.client.restservices;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;


@GinModules({ RestGinjectorModule.class })
public interface RestGinjector extends Ginjector {

	EventBus getEventBus();
}
