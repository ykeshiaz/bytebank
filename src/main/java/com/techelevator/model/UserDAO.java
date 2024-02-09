package com.techelevator.model;

import java.util.List;

public interface UserDAO {

	public void saveUser(String userName, String password);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public User getUserByUserName(String userName);

	public List<User> getAllUsers();

}
