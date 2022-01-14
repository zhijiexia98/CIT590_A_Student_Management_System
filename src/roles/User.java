package roles;

import files.FileInfoReader;

public abstract class User {
	
	/**
	 * user id
	 */
	private String id;
	
	/**
	 * name of the user
	 */
	private String name;
	
	/**
	 * login name
	 */
	private String username;
	
	/**
	 * login password
	 */
	private String password;
	
	//constructor
	/**
	 * construct all the information from the array read from the .txt file
	 * @param given_array
	 */
	public User(String[] given_array) {
		this.id = given_array[0].trim();
		this.name = given_array[1].trim();
		this.username = given_array[2].trim();
		this.password = given_array[3].trim();
	}
	
	/**
	 * if we want to create a new user
	 * call this method
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public User(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	
	//setters
	/**
	 * set the user id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * set the user name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * set the username
	 */
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	//getters
	/**
	 * get the user id
	 * @param id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * get the user name
	 * @param name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get the username
	 */
	
	public String getUsername() {
		return username;
	}
	
	/**
	 * get the password
	 */
	public String getPassword() {
		return password;
	}
	
	
}
