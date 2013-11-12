/*
 */

package albert.lacambra.server.auth;

import albert.lacambra.server.models.Person;

import com.googlecode.objectify.Key;


/**
 * This class authoritatively manages the identity of a user through the context of a request.
 * It works in concert with the BraceletFilter to establish an identity.
 */
public class Bracelet
{
	/**
	 * If we are logged in, this will be present.
	 */
	Key<Person> meKey;
	
	public Bracelet(){
		
	}


	/**
	 * @return true if we are logged in
	 */
	public boolean isLoggedIn() {
		return this.meKey != null;
	}

	/**
	 * Log in our true identity.
	 */
	public void login(Key<Person> who) {
		this.meKey = who;
	}
	
	public Key<Person> getMeKey() {
		return meKey;
	}
}
