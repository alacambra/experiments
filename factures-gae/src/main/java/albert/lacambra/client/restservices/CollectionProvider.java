package albert.lacambra.client.restservices;

import java.util.Collection;

import com.google.web.bindery.event.shared.EventBus;

public interface CollectionProvider<T> {

	T get(Long id);
	Collection<T> get();
	void configure(Collection<T> collection);
}
