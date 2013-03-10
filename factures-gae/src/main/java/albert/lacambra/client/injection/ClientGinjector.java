package albert.lacambra.client.injection;

import com.google.gwt.inject.client.GinModules;

import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;
import albert.lacambra.client.presenters.DefaultPresenter;

@GinModules({ ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<DefaultPresenter> getDefaultPresenter();

	
}
