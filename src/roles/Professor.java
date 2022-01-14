package roles;

public class Professor extends User {

	/**
	 * Initial constructor for the professor array
	 * @param given_array
	 */
	public Professor(String[] given_array) {
		super(given_array[1].trim(),given_array[0].trim(),given_array[2].trim(),given_array[3].trim());
	}
	
	/**
	 * constructor for adding new professor
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public Professor(String id, String name, String username, String password) {
		super(id, name, username, password);
	}

}
