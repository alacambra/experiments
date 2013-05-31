package albert.lacambra.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import albert.lacambra.server.models.Budget;
import albert.lacambra.server.models.Invoice;

@Provider
@Produces({MediaType.APPLICATION_JSON, "*/*"})
public class BudgetReaderWriter implements MessageBodyWriter<Budget>, MessageBodyReader<Budget>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		
		return type.isAssignableFrom(Budget.class);
	}

	@Override
	public long getSize(Budget t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		
		return -1;
	}

	@Override
	public void writeTo(Budget t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		
		ObjectMapper m = new ObjectMapper();
		entityStream.write(m.writeValueAsBytes(t));
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(Invoice.class);
	}

	@Override
	public Budget readFrom(Class<Budget> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		ObjectMapper m = new ObjectMapper();
		
		return m.readValue(entityStream, Budget.class);
	}
}












































