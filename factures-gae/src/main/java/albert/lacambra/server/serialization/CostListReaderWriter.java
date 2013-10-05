package albert.lacambra.server.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import albert.lacambra.server.models.Cost;
import albert.lacambra.server.models.CostDTO;
import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.IndividualCostDTO;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostDTO;

@Provider
@Produces({MediaType.APPLICATION_JSON, "*/*"})
public class CostListReaderWriter 
implements MessageBodyWriter<List<Cost<?>>>, MessageBodyReader<List<Cost<?>>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		boolean isWritable;

		if (List.class.isAssignableFrom(type)
				&& genericType instanceof ParameterizedType) {

			ParameterizedType parameterizedType = (ParameterizedType) genericType;

			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());

			try {
				Class<?> t = (Class<?>) actualTypeArgs[0];
				t.asSubclass(Cost.class);
				isWritable = true;
			} catch (ClassCastException e) {
				isWritable = false;
			} 

		} else {
			isWritable = false;
		}

		return isWritable;

	}

	@Override
	public long getSize(List<Cost<?>> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {

		return -1;
	}

	@Override
	public void writeTo(List<Cost<?>> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		ObjectMapper m = new ObjectMapper();
		List<CostDTO<?>> dtos = new ArrayList<CostDTO<?>>();

		for (Cost<?> c : t) {
			dtos.add(c.getDTO());
		}

		entityStream.write(m.writeValueAsBytes(dtos));
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
	public List<Cost<?>> readFrom(Class<List<Cost<?>>> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {

		ObjectMapper m = new ObjectMapper();

		if (List.class.isAssignableFrom(type)
				&& genericType instanceof ParameterizedType) {

			ParameterizedType parameterizedType = (ParameterizedType) genericType;

			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());

			Class<?> t = (Class<?>) actualTypeArgs[0];
			if ( t.isAssignableFrom(IndividualCost.class )) {

				List<Cost<?>> costs = new ArrayList<Cost<?>>();
				List<CostDTO<?>> dtos = m.readValue(entityStream, List.class);

				for (CostDTO<?> c : dtos) {
					costs.add(new IndividualCost((IndividualCostDTO)c));
				}

				return costs;
			} else if ( t.isAssignableFrom(PeriodicCost.class )) {

				List<Cost<?>> costs = new ArrayList<Cost<?>>();
				List<CostDTO<?>> dtos = m.readValue(entityStream, List.class);

				for (CostDTO<?> c : dtos) {
					costs.add(new PeriodicCost((PeriodicCostDTO)c));
				}

				return costs;
			} 

		}

		throw new RuntimeException("Not assignable type: " + type.getCanonicalName());
	}
}













































