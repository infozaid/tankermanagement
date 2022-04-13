package com.sms.enterprise.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;



public interface OrderDao {
	
	public void save(Order order);
	public List<Order> findAll();
	public List<Order> findByUser(User user);
	public Long findOrderById(String user_id);
    public Long findOrderByDate(int user_id,String date);
    public String findOrderByLastDate(int user_id);
    public int approveOrder(int order_id);
    public int pendingOrder(int order_id);
    public int halfapproveOrder(int order_id);
    public List<Order> findAllApproveOrder();
    public List<Order> findAllPendingOrder();
    public List<Order> findHalfApproveOrder();
    public JasperPrint ApproveOrderReport(OrderBean order) throws SQLException, JRException, IOException;
    public List<Order> findPaginated();
    public Order searchAll(String keyword);
    public List<Order> findBeforeDayOrder();
    public List<Order> searchOrder(String theSearchName);
    public List<Order> searchPendingOrder(String theSearchName);
    public List<Order> searchRequestOrder(String theSearchName);
}
