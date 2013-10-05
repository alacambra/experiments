package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONValue;

public interface IsJsonSerializable {

	JSONValue serializeToJsonValue();
	IsJsonSerializable loadFromJson(JSONValue jsonValue);
	
}
