/*
 */

package albert.lacambra.server.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import albert.lacambra.server.models.Person;

import com.google.inject.servlet.RequestScoped;
import com.googlecode.objectify.Key;


/**
 * This class authoritatively manages the identity of a user through the context of a request.
 * It works in concert with the BraceletFilter to establish an identity.
 */
@RequestScoped
@NoArgsConstructor
public class Bracelet
{
	/**
	 * If we are logged in, this will be present.
	 */
	@Getter
	Key<Person> meKey;


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
