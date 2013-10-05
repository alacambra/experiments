package albert.lacambra.client.restservices.utils;

public class Credentials {
	
	private String username;
	private String secret;
	
	public Credentials() { }
	
	public Credentials( String username, String secret ) { 
		
		this.username = username;
		this.secret = secret;
		
	}
	
	public void setCredentials(String username, String secret) {
		this.username = username;
		this.secret = secret;
	}

	public String getUsername() {
		return username;
	}
	
	public String getSecret() {
		return secret;
	}
}
