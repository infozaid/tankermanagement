package com.sms.enterprise.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.beans.UserDetail;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.status.ReportStatus;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperDao {
	
	public JasperPrint exportPdfFile(OrderBean order) throws SQLException, JRException, IOException;
    public JasperPrint ApproveOrderReport(OrderBean order) throws SQLException, JRException, IOException;
    public JasperPrint PendingOrderReport(OrderBean order) throws SQLException, JRException, IOException;
    public JasperPrint OrderSummaryReport(OrderBean order) throws SQLException, JRException, IOException;
    public JasperPrint UserHistoryReport(OrderBean order) throws SQLException, JRException, IOException;

}
