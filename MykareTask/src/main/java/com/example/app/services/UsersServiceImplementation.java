package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.entities.Users;
import com.example.app.repositories.UsersRepository;

@Service
@Transactional
public class UsersServiceImplementation implements UsersService {
    
	@Autowired
	UsersRepository repo;

	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "user is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)== null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user= repo.findByEmail(email);
		String db_password= user.getPassword();
		if(db_password.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public List<Users> fetchAllUsers() {
		List<Users> userlist=repo.findAll();
		return userlist;
	}

	@Override
	public String deleteUserByEmail(String email) {
		repo.deleteByEmail(email);
        return "deletesucess";
	}

}
