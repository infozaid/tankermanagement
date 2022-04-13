package com.sms.enterprise.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;
import com.sms.enterprise.model.UserTask;
import com.sms.enterprise.service.OrderService;
import com.sms.enterprise.service.UserService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;

@Controller
@RestController
// @RequestMapping("/orders")
public class OrderController {

	@Autowired
    private	OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUserTask() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("order");
		return modelAndView;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView newUserTask1() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ORDER");
		modelAndView.setViewName("order");
		return modelAndView;
	}
	

	
	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
	public ModelAndView showMyTask(@RequestParam int id) {
		id=getUser().getId();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("user", userService.findUser(id));
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("orderUsers", orderService.findByUser(userService.findUser(id)));
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.setViewName("order");
		return modelAndView;
	}
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date); 
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd hh.mm aa"); 
	 DateFormat df=new SimpleDateFormat("yyy-MM-dd HH:mm");
	 String dateString = df.format(new Date()).toString();
	 // for testing purpose
	 String dateString1="2020-07-01";
	// String formattedDate=dateObj.format(dtf);
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ModelAndView saveUserTask(@Valid Order orderUser, BindingResult bindingResult,Model model) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ORDER");
		User user=new User();
		int  id=getUser().getId();
		String unitno=getUser().getUnitno();
		user.setId(id);
		orderUser.setUser(user);
		//orderUser.setDateCreated(dtf.format(now));
		/* orderUser.setDateCreated(dateString); */
		 orderUser.setDateCreated(dateString);
		String date=orderUser.getDateCreated();
		Long count=orderService.findOrderByDate(id, date);
		
		
		
		if(count==null) {
			orderService.save(orderUser);
		    model.addAttribute("message", "Successfully Receive Your 1st Request we will deliver your Tanker in 48 hours");
			modelAndView.setViewName("order");
		}
		
		
		else if(count < 2) {
			String dat2=orderService.findOrderByLastDate(id);
			 Date start = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(dat2);
			 Date end = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(date);
			 Calendar c=Calendar.getInstance();
			 c.setTime(start);
			 c.add(Calendar.DATE,3);
			if(c.getTime().compareTo(end)<0) {
			orderService.save(orderUser);
		    model.addAttribute("message1", "Successfully Receive Your 2nd Request we will deliver your Tanker in 48 hours");
		    
			}else {
				model.addAttribute("message2", "We can not process your second request before 3 days! please comeback after 3 days");
				modelAndView.setViewName("order");
			}
			modelAndView.setViewName("order");
		}
		
		else if(count < 3) {
			String dat2=orderService.findOrderByLastDate(id);
			 Date start = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(dat2);
			 Date end = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(date);
			 Calendar c=Calendar.getInstance();
			 c.setTime(start);
			 c.add(Calendar.DATE,3);
			if(c.getTime().compareTo(end)<0) {
			orderService.save(orderUser);
		    model.addAttribute("message1", "Successfully Receive Your 3rd Request we will deliver your Tanker in 48 hours");
			}else {
				model.addAttribute("message2", "We can not process your 3rd request before 3 days! please comeback after 3 days");
				modelAndView.setViewName("order");
			}
			modelAndView.setViewName("order");
		}
		
		else if(count < 4) {
			String dat2=orderService.findOrderByLastDate(id);
			 Date start = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(dat2);
			 Date end = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
	                 .parse(date);
			 Calendar c=Calendar.getInstance();
			 c.setTime(start);
			 c.add(Calendar.DATE,3);
			if(c.getTime().compareTo(end)<0) {
			orderService.save(orderUser);
		    model.addAttribute("message1", "Successfully Receive Your 4th Request we will deliver your Tanker in 48 hours");
			}else {
				model.addAttribute("message2", "We can not process your 4th request before 3 days! please comeback after 3 days");
				modelAndView.setViewName("order");
			}
			modelAndView.setViewName("order");
		}
		
		else {
			
			model.addAttribute("errormessage", "Sorry! You have already sent 4 request in this month");
			modelAndView.setViewName("order");
			System.out.println("User cannot proceed");
			}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("order");
		}
		

		/*
		 * else { orderService.save(orderUser); modelAndView.addObject("successMessage",
		 * "Registration Successful."); modelAndView.setViewName("order"); }
		 */
		 
		return modelAndView;
	}
	
	
	
	

	
	
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user =(User) userService.findUserByEmail(auth.getName());
		return user;
	}
	
	
	
	
}
