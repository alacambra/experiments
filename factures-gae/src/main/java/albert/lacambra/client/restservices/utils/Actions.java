package albert.lacambra.client.restservices.utils;

public class Actions {
	public static interface ActionWithValue<T> {
		public void run(T value);
	}
}
