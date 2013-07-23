package albert.lacambra.server;

import org.restlet.resource.Get;

public class HelloWorldResource {

	@Get
	public String represent() {
		return "hello, world (from the cloud!)";
	}

}
