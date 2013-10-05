package albert.lacambra.server.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import albert.lacambra.server.models.Cost;
import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.IndividualCostDTO;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostDTO;

@Provider
@Produces({MediaType.APPLICATION_JSON, "*/*"})
public class CostReaderWriter 
implements MessageBodyWriter<Cost<?>>, MessageBodyReader<Cost<?>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		try {
			type.asSubclass(Cost.class);
			return true;
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public long getSize(Cost<?> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		return -1;
	}

	@Override
	public void writeTo(Cost<?> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		ObjectMapper m = new ObjectMapper();
		entityStream.write(m.writeValueAsBytes(t.getDTO()));
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		try {
			type.asSubclass(Cost.class);
			return true;
		} catch (ClassCastException e) {
			return false;
		}

	}

	@Override
	public Cost<?> readFrom(Class<Cost<?>> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {

		ObjectMapper m = new ObjectMapper();
		try {
			if ( type.isAssignableFrom(IndividualCost.class )) {
				return new IndividualCost(m.readValue(entityStream, IndividualCostDTO.class));
			} else if ( type.isAssignableFrom(PeriodicCost.class )) {
				return new PeriodicCost(m.readValue(entityStream, PeriodicCostDTO.class));
			} 
		} catch ( Exception e) {
			throw new WebApplicationException(e, Response.serverError().entity(e.getMessage()).build());
		}

		throw new WebApplicationException(
				Response.serverError().entity("Not assignable type: " + type.getCanonicalName()).build());
	}
}













































