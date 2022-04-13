package com.sms.enterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sms.enterprise.beans.PageWrapper;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;
import com.sms.enterprise.service.OrderService;
import com.sms.enterprise.service.UserService;

@Controller
@RequestMapping("/admin/ViewOrders")
public class ViewOrderController {

	@Autowired
    private	OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView newUserTask(Model model,Pageable pageable,@Param("keyword") String keyword) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		// Order searchList=orderService.searchAll(keyword);
		// model.addAttribute("searchList",searchList);
		List<Order> orderList=orderService.findPaginated();
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderList.size() ? orderList.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderList.subList(start, end), pageable, orderList.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/view");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("viewuserorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewhalforder", method = RequestMethod.GET)
	public ModelAndView viewHalfOrder() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.findHalfApproveOrder());
		modelAndView.addObject("mode", "MODE_HALFVIEW");
		modelAndView.setViewName("viewuserorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
	public ModelAndView ApproveOrder(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.POST)
	public ModelAndView ApproveOrder1(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/pending/{id}", method = RequestMethod.GET)
	public ModelAndView PendingOrder(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.pendingOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/pending/{id}", method = RequestMethod.POST)
	public ModelAndView PendingOrder1(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.pendingOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/halfapprove/{id}", method = RequestMethod.GET)
	public ModelAndView HalfApproveOrder(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.halfapproveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/halfapprove/{id}", method = RequestMethod.POST)
	public ModelAndView HalfApproveOrder1(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.halfapproveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/view");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewapproveorder", method = RequestMethod.GET)
	public ModelAndView viewApproveOrder(Model model,Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		List<Order> orderListApprove=orderService.findAllApproveOrder();
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderListApprove.size() ? orderListApprove.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderListApprove.subList(start, end), pageable, orderListApprove.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/viewapproveorder");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("viewapproveorders");
	    //System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchorder", method = RequestMethod.POST)
	public ModelAndView searcOrder(@RequestParam("theSearchName") String theSearchName,Model model,Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		List<Order> orderListApprove=orderService.searchOrder(theSearchName);
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderListApprove.size() ? orderListApprove.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderListApprove.subList(start, end), pageable, orderListApprove.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/viewapproveorders");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("viewapproveorders");
	    //System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewpendingorder", method = RequestMethod.GET)
	public ModelAndView viewPendingOrder(Model model,Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		List<Order> orderListPending=orderService.findAllPendingOrder();
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderListPending.size() ? orderListPending.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderListPending.subList(start, end), pageable, orderListPending.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/viewpendingorder");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("viewpendingorders");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/approvepending/{id}", method = RequestMethod.GET)
	public ModelAndView approvePendingOrder(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/viewpendingorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/approvepending/{id}", method = RequestMethod.POST)
	public ModelAndView approvePendingOrder1(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/viewpendingorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView viewHomePage(Model model,Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		List<Order> orderList=orderService.findPaginated();
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderList.size() ? orderList.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderList.subList(start, end), pageable, orderList.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/page");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("page");
		return modelAndView;	
	}
	
	
	@RequestMapping(value = "/alertorder", method = RequestMethod.GET)
	public ModelAndView viewAlertPage(Model model,Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		List<Order> orderList=orderService.findBeforeDayOrder();
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > orderList.size() ? orderList.size() : (start + pageable.getPageSize());
		Page<Order> pages = new PageImpl<Order>(orderList.subList(start, end), pageable, orderList.size());
		PageWrapper<Order> page = new PageWrapper<Order>(pages, "/admin/ViewOrders/alertorder");
		model.addAttribute("orders", page.getContent());
        model.addAttribute("page", page);
		modelAndView.setViewName("alertorder");
		return modelAndView;	
	}
	@RequestMapping(value = "/approvealert/{id}", method = RequestMethod.GET)
	public ModelAndView approveAlertOrder1(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/alertorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/approvealert/{id}", method = RequestMethod.POST)
	public ModelAndView approveAlertOrder(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("ordersview", orderService.approveOrder(id));
		modelAndView.setViewName("redirect:/admin/ViewOrders/alertorder");
		//System.out.println(orderService.findAll());
		return modelAndView;
	}
	
	
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user =(User) userService.findUserByEmail(auth.getName());
		return user;
	}
}
