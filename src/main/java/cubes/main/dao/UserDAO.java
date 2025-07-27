package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Role;
import cubes.main.entity.User;

public interface UserDAO {

	public List<User> getAllUsers(); // iz paketa entity.User
	
	public void saveUser(User user); // enable i disable isto preko ove
	
	public void deleteUser(String username); // prosledjuje se id
		
	public User getUser(String username);
	
	// ne pravimo poseban DAO za Role nego ovde:
	public List<Role> getUserRoles(); 
	
}
