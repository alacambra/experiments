package albert.lacambra.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.server.models.PersistedInvoice;

@Provider
@Produces({MediaType.APPLICATION_JSON, "*/*"})
public class InvoiceListReaderWriter 
implements MessageBodyWriter<List<DTOInvoice>>, MessageBodyReader<List<DTOInvoice>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		
	    boolean isWritable;
	    
	    if (List.class.isAssignableFrom(type)
	        && genericType instanceof ParameterizedType) {
	    	
	      ParameterizedType parameterizedType = (ParameterizedType) genericType;
	      
	      Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
	      
	      isWritable = (actualTypeArgs.length == 1 &&
	    		  actualTypeArgs[0].equals(DTOInvoice.class));
	      
	    } else {
	      isWritable = false;
	    }

	    return isWritable;
	}

	@Override
	public long getSize(List<DTOInvoice> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		
		return -1;
	}

	@Override
	public void writeTo(List<DTOInvoice> t, Class<?> type, Type genericType,
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
		return type.isAssignableFrom(PersistedInvoice.class);
	}

	@Override
	public List<DTOInvoice> readFrom(Class<List<DTOInvoice>> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		ObjectMapper m = new ObjectMapper();
		
		return m.readValue(entityStream, List.class);
	}
}













































