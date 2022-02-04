package com.revature.services;

import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	private UserDAO userDAO = new UserDAO();

	public static User login(String username, String password) {
		// first we need the help of our UserDAO to retrieve user information by their username
		User u = UserDAO.getByUsername(username);
		// check if user exists
		if (u != null) {
			/*
			System.out.println(u.getUsername());
			System.out.println(u.getPassword());
			*/
			// check to make sure credentials match

			if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
				return u;
			}
		}
		System.out.println("Credentials do not match"); // this would be a great place to use a custom exception
		return null;
	}

	public User Register(String firstName, String lastName, String userName,String password, String role){
	User U = new User(firstName,lastName,userName,password,Role.valueOf(role));
	U = userDAO.add(U);
	return U;
	}

	public Optional<User> getByUsername(String username) {
		return Optional.empty();
	}
}
