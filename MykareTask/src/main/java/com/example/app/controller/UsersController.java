package com.example.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.entities.Users;
import com.example.app.services.UsersService;

@Controller
public class UsersController 
{
	@Autowired
	UsersService userv;
	
        //code for User Registration (Email should be unique and don't allow duplicate user to register)
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
	    boolean userstatus = userv.emailExists(user.getEmail());
	    if (userstatus == false) {
	        userv.addUser(user);
	        return "registersucess";
	    } else {
	        return "registerfail";
	    }
	}

	//code for validate the user by passing Email and Password.
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password ) {
		if(userv.validateUser(email, password) == true)
		{
			return "userhome";
		}
		else {
			return "loginfail";
		}
	}

	//code to get all the users registered
	@GetMapping("/map-viewusers")
	public String viewUsers(Model model)
	{
		List<Users> userslist=userv.fetchAllUsers();
		model.addAttribute("userslist",userslist);
		return "displayusers";	
	}

	@GetMapping("/map-delete")
	public String mapDelete() {
	    return "deleteuser";
	}

	//code to delete a user based on the email id passed
	@RequestMapping(value = "/delete/{email}", method = {RequestMethod.DELETE, RequestMethod.POST} )
	public String deleteUser(String email) {
	    String msg = userv.deleteUserByEmail(email);
	    return msg;
	}
	

}
