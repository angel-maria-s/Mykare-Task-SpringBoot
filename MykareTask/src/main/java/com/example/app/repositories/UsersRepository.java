package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {

	public Users findByEmail(String email);
	void deleteByEmail(String email);
}



