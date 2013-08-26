package albert.lacambra.client.restservices;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import albert.lacambra.client.events.PeriodicCostAddedEvent;
import albert.lacambra.client.events.PeriodicCostDeletedEvent;
import albert.lacambra.client.models.PeriodicCost;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;
import albert.lacambra.shared.ResourceLocator;

public class PeriodicCostProvider implements CollectionProvider<PeriodicCost> {

	HashMap<Long, PeriodicCost> individualCost = new HashMap<Long, PeriodicCost>();
	private EventBus eventBus;
	PeriodicCostService periodicCostService;

	@Inject
	public PeriodicCostProvider(EventBus eventBus) {
		
		this.eventBus = eventBus;
		
		Resource resource = new Resource( GWT.getHostPageBaseURL()+ "rest/" + ResourceLocator.periodicCostBase);

		periodicCostService = GWT.create(PeriodicCostService.class);
		((RestServiceProxy)periodicCostService).setResource(resource);

	}

	@Override
	public void configure(Collection<PeriodicCost> individualCost) {

		for ( PeriodicCost i : individualCost) {
			this.individualCost.put(i.getId(), i);
		}

//		loadHandlers();

	}

	private void loadHandlers() {

		this.eventBus.addHandler(PeriodicCostAddedEvent.TYPE, new PeriodicCostAddedEvent.PeriodicCostAddedHandler() {

			@Override
			public void onPeriodicCostAdded(PeriodicCostAddedEvent event) {
				individualCost.put(event.getPeriodicCost().getId(), event.getPeriodicCost());
			}
		});

		this.eventBus.addHandler(PeriodicCostDeletedEvent.TYPE, new PeriodicCostDeletedEvent.PeriodicCostDeletedHandler() {

			@Override
			public void onPeriodicCostDeleted(PeriodicCostDeletedEvent event) {
				
				PeriodicCost i = individualCost.remove(event.getPeriodicCost().getId());

				if ( i == null ) {
					Log.info("budget not found in provider " + event.getPeriodicCost().getId());
				}

			}
		});
	}
	
	public void getPeriodicCosts(int year, final AsyncCallback<List<PeriodicCost>> callback) {
		periodicCostService.getPeriodicCosts(year, new MethodCallback<List<PeriodicCost>>() {
			
			@Override
			public void onSuccess(Method method, List<PeriodicCost> response) {
				callback.onSuccess(response);
				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(new ResponseException(method.getResponse(), exception));
			}
		});
	}
	
	public void addPeriodicCost(PeriodicCost c, final AsyncCallback<Long> callback) {
		periodicCostService.savePeriodicCost(c, new MethodCallback<Long>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(new ResponseException(method.getResponse(), exception));
				
			}

			@Override
			public void onSuccess(Method method, Long response) {
				callback.onSuccess(response);
			}
		});
	}

	@Override
	public PeriodicCost get(Long id) {

		return individualCost.get(id);

	}

	@Override
	public Collection<PeriodicCost> get() {
		return individualCost.values();
	}
	
	public HashMap<Long, PeriodicCost> getPeriodicCosts() {
		return individualCost;
	}

}
