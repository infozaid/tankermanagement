package com.sms.enterprise.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.beans.UserDetail;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.status.ReportStatus;
import com.sms.enterprise.status.ReportStatus.STATUS;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Repository
public class JasperDaoImpl implements JasperDao {

	@Autowired
	BasicDataSource ds;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public JasperPrint exportPdfFile(OrderBean orders) throws SQLException, JRException, IOException {
		Connection conn = ds.getConnection();
		String path = resourceLoader.getResource("classpath:ReceiveOrder.jrxml").getURI().getPath();
        InputStream file=getClass().getResourceAsStream("/ReceiveOrder.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fromDate", orders.fromDate);
		parameters.put("toDate", orders.toDate);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		conn.close();
		return print;
	}
	
	public JasperPrint ApproveOrderReport(OrderBean orders) throws SQLException, JRException, IOException{
		 Connection conn = ds.getConnection();

			String path = resourceLoader.getResource("classpath:approveorder.jrxml").getURI().getPath();
			InputStream file=getClass().getResourceAsStream("/approveorder.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file);

			// Parameters for report
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("fromDate", orders.fromDate);
			parameters.put("toDate", orders.toDate);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			conn.close();
			return print;
		 
	 }
	
	 public JasperPrint PendingOrderReport(OrderBean orders) throws SQLException, JRException, IOException{
		 Connection conn = ds.getConnection();

			String path = resourceLoader.getResource("classpath:pendingorder.jrxml").getURI().getPath();
			InputStream file=getClass().getResourceAsStream("/pendingorder.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file);
			
			// Parameters for report
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("fromDate", orders.fromDate);
			parameters.put("toDate", orders.toDate);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			conn.close();
			return print;
	 }
	 
	 public JasperPrint OrderSummaryReport(OrderBean order) throws SQLException, JRException, IOException{
		 Connection conn = ds.getConnection();

			String path = resourceLoader.getResource("classpath:orders_Summary.jrxml").getURI().getPath();
			InputStream file=getClass().getResourceAsStream("/orders_Summary.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file);

			// Parameters for report
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("fromDate", order.fromDate);
			parameters.put("toDate", order.toDate);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			conn.close();
			return print;
	 }
	 
	 public JasperPrint UserHistoryReport(OrderBean order) throws SQLException, JRException, IOException{
		 Connection conn = ds.getConnection();

			String path = resourceLoader.getResource("classpath:user_history_report.jrxml").getURI().getPath();
			InputStream file=getClass().getResourceAsStream("/user_history_report.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file);

			// Parameters for report
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("unitno", order.getUnitno());
			parameters.put("fromDate", order.fromDate);
			parameters.put("toDate", order.toDate);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			conn.close();
			return print;
	 }

}
