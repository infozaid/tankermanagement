package com.sms.enterprise.controller;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import javax.validation.Valid;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.Task;
import com.sms.enterprise.model.User;
import com.sms.enterprise.model.UserTask;
import com.sms.enterprise.service.OrderService;
import com.sms.enterprise.service.RoleService;
import com.sms.enterprise.service.TaskService;
import com.sms.enterprise.service.UserService;
import com.sms.enterprise.service.UserTaskService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserTaskService userTaskService;
	
	@Autowired
	OrderService orderService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value={"/index"}, method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User unitNoExist=userService.findUserByUnitNo(user.getUnitno());
		User userExists =  userService.findUserByEmail(user.getEmail());
		
		if(unitNoExist !=null) {
			bindingResult
			.rejectValue("unitno", "error.user", 
					"This (Unit No) is already exist"+" Please use your own (Unit No)!");
		}
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Email has already been taken"
							+ " Check your details!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Registration Successful.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}


	@RequestMapping(value="/access-denied", method = RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("403");
		return modelAndView;
	}


	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		
		List<Role> role = new ArrayList<Role>();
		List<Role> role2 = new ArrayList<Role>();
		List<Role> role3 = new ArrayList<Role>();
		role = roleService.findRole("ADMIN");
		role2 = roleService.findRole("USER");
		role3 = roleService.findRole("TANKERADMIN");
		List<User> users = new ArrayList<>();
		List<User> users2 = new ArrayList<>();
		List<User> user3 = new ArrayList<>();
		users = userService.findUserbyRole(role);
		users2 = userService.findUserbyRole(role2);
		user3 = userService.findUserbyRole(role3);
		List<Task> tasks = new ArrayList<>();
		tasks = taskService.findAll();
		List<Order> alertCount=new ArrayList<Order>();
		alertCount=orderService.findBeforeDayOrder();
		int taskCount = tasks.size();
		int adminCount = users.size();
		int userCount = users2.size();
		int superCount=user3.size();
		int alertCoun=alertCount.size();
		modelAndView.addObject("adminCount", adminCount);//Authentication for NavBar
		modelAndView.addObject("userCount", userCount);//Authentication for NavBar
		modelAndView.addObject("taskCount", taskCount);//Authentication for NavBar
		modelAndView.addObject("superCount", superCount);//Authentication for NavBar
		modelAndView.addObject("alertCount",alertCoun);
		//-----------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loginUser =  (User) userService.findUserByEmail(auth.getName());
		modelAndView.addObject("control", loginUser.getRole().getRole());//Authentication for NavBar
		modelAndView.addObject("auth", loginUser);
		List<UserTask> userTasks = new ArrayList<>();
		userTasks = userTaskService.findByUser(loginUser);
		modelAndView.addObject("userTaskSize", userTasks.size());
		modelAndView.setViewName("home");
		return modelAndView;
	}
}
