package com.sms.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sms.enterprise.model.User;
import com.sms.enterprise.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allUsers() {
		ModelAndView modelAndView = new ModelAndView();
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.setViewName("user");
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user =(User) userService.findUserByEmail(auth.getName());
		return user;
	}
}
