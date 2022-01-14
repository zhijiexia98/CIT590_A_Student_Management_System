package roles;

public class Admin extends User{
	
	
	/**
	 * Initial constructor for the admin array
	 * @param given_array
	 */
    public Admin(String [] given_array){
        super(given_array);
    }
    
	/**
	 * constructor for adding new admin
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public Admin(String id, String name, String username, String password) {
		super(id, name, username, password);
	}
}
