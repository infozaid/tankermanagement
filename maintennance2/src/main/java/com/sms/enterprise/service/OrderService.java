package com.sms.enterprise.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.dao.OrderDao;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderDao orderDao;
	
	public void save(Order order) {
		orderDao.save(order);
	}
	
	public List<Order> findAll(){
		return orderDao.findAll();
	}
	
	public List<Order> findByUser(User user){
		return orderDao.findByUser(user);
	}
	
	public Long findOrderById(String unitno) {
		return orderDao.findOrderById(unitno);
	}
	
	public Long findOrderByDate(int user_id,String date) {
		return orderDao.findOrderByDate(user_id, date);
	}
	
	public int approveOrder(int id) 
	{
		return orderDao.approveOrder(id);
	}
	
	public int pendingOrder(int id) {
		return orderDao.pendingOrder(id);
		
	}
	
	public int halfapproveOrder(int id) {
		return orderDao.halfapproveOrder(id);
	}
	public List<Order> findHalfApproveOrder(){
		return orderDao.findHalfApproveOrder();
	}
	
	public List<Order> findAllApproveOrder(){
		return orderDao.findAllApproveOrder();
	}
	
	public List<Order> findAllPendingOrder(){
		return orderDao.findAllPendingOrder();
	}
	
	public String findOrderByLastDate(int user_id) {
		return orderDao.findOrderByLastDate(user_id);
	}
	
	 public JasperPrint ApproveOrderReport(OrderBean orders) throws SQLException, JRException, IOException{
		   return orderDao.ApproveOrderReport(orders);
	 }
	 
	 public List<Order> findPaginated(){
		 return orderDao.findPaginated();
	 }
	 
	 public Order searchAll(String keyword){
		 return orderDao.searchAll(keyword);
	 }
	 
	 public List<Order> findBeforeDayOrder(){
		 return orderDao.findBeforeDayOrder();
	 }
	 
	 public List<Order> searchOrder(String theSearchName){
		 return orderDao.searchOrder(theSearchName);
	 }
	 public List<Order> searchPendingOrder(String theSearchName){
		 return orderDao.searchPendingOrder(theSearchName);
	 }
	 public List<Order> searchRequestOrder(String theSearchName){
		 return orderDao.searchRequestOrder(theSearchName);
	 }
}
