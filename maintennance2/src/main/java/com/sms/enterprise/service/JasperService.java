package com.sms.enterprise.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.beans.UserDetail;
import com.sms.enterprise.dao.JasperDao;
import com.sms.enterprise.dao.OrderDao;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.status.ReportStatus;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class JasperService {
	
	@Autowired
	JasperDao jasperDao;
	
	
	public JasperPrint exportPdfFile(OrderBean orders) throws SQLException, JRException, IOException{
		return jasperDao.exportPdfFile(orders);
	}
	
	
	public JasperPrint ApproveOrderReport(OrderBean orders) throws SQLException, JRException, IOException{
		   return jasperDao.ApproveOrderReport(orders);
	 }
	
	public JasperPrint PendingOrderReport(OrderBean orders) throws SQLException, JRException, IOException{
		return jasperDao.PendingOrderReport(orders);
	}
    public JasperPrint OrderSummaryReport(OrderBean order) throws SQLException, JRException, IOException{
		return jasperDao.OrderSummaryReport(order); 
	 }
    public JasperPrint UserHistoryReport(OrderBean order) throws SQLException, JRException, IOException{
    	return jasperDao.UserHistoryReport(order);
    }

}
