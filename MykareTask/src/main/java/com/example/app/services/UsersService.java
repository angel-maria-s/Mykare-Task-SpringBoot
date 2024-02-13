package com.example.app.services;

import java.util.List;

import com.example.app.entities.Users;

public interface UsersService {
	 public String addUser(Users user);
     public boolean emailExists(String email);
     public boolean validateUser(String email, String password);
	 public Users getUser(String email);
	 public List<Users> fetchAllUsers();
	 public String deleteUserByEmail(String email);
	
}
