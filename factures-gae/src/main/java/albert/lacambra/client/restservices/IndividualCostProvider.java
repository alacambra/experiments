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

import albert.lacambra.client.events.IndividualCostAddedEvent;
import albert.lacambra.client.events.IndividualCostDeletedEvent;
import albert.lacambra.client.models.IndividualCost;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;
import albert.lacambra.shared.ResourceLocator;

public class IndividualCostProvider implements CollectionProvider<IndividualCost> {

	HashMap<Long, IndividualCost> individualCost = new HashMap<Long, IndividualCost>();
	private EventBus eventBus;
	@Inject RestServices restServices;
	IndividualCostService individualCostService;

	@Inject
	public IndividualCostProvider(EventBus eventBus) {
		
		this.eventBus = eventBus;
		
		Resource resource = new Resource( GWT.getHostPageBaseURL()+ "rest/" + ResourceLocator.individualCostBase);

		individualCostService = GWT.create(IndividualCostService.class);
		((RestServiceProxy)individualCostService).setResource(resource);

	}

	@Override
	public void configure(Collection<IndividualCost> individualCost) {

		for ( IndividualCost i : individualCost) {
			this.individualCost.put(i.getId(), i);
		}

//		loadHandlers();

	}

	private void loadHandlers() {

		this.eventBus.addHandler(IndividualCostAddedEvent.TYPE, new IndividualCostAddedEvent.IndividualCostAddedHandler() {

			@Override
			public void onIndividualCostAdded(IndividualCostAddedEvent event) {
				individualCost.put(event.getIndividualCost().getId(), event.getIndividualCost());
			}
		});

		this.eventBus.addHandler(IndividualCostDeletedEvent.TYPE, new IndividualCostDeletedEvent.IndividualCostDeletedHandler() {

			@Override
			public void onIndividualCostDeleted(IndividualCostDeletedEvent event) {
				
				IndividualCost i = individualCost.remove(event.getIndividualCost().getId());

				if ( i == null ) {
					Log.info("budget not found in provider " + event.getIndividualCost().getId());
				}

			}
		});
	}
	
	public void getIndividualCosts(int year, final AsyncCallback<List<IndividualCost>> callback) {
		individualCostService.getIndividualCosts(year, new MethodCallback<List<IndividualCost>>() {
			
			@Override
			public void onSuccess(Method method, List<IndividualCost> response) {
				callback.onSuccess(response);
				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(new ResponseException(method.getResponse(), exception));
			}
		});
	}
	
	public void addIndividualCost(IndividualCost c, final AsyncCallback<Long> callback) {
		individualCostService.saveIndividualCost(c, new MethodCallback<Long>() {

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
	public IndividualCost get(Long id) {

		return individualCost.get(id);

	}

	@Override
	public Collection<IndividualCost> get() {
		return individualCost.values();
	}
	
	public HashMap<Long, IndividualCost> getIndividualCosts() {
		return individualCost;
	}

}
