package com.sms.enterprise.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.beans.UserDetail;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;
import com.sms.enterprise.service.JasperService;
import com.sms.enterprise.service.UserService;
import com.sms.enterprise.status.ReportStatus;
import com.sms.enterprise.status.ReportStatus.STATUS;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;




@Controller
@RequestMapping("/admin")
public class JasperController {
	
	@Autowired
	JasperService jasperService;
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(value = "/rep", method = RequestMethod.GET)
	public ModelAndView newUserTask() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders", new OrderBean());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.setViewName("report");
		return modelAndView;
	}
	
	
	
	
	
	 @RequestMapping(value = "/export", method = RequestMethod.POST)
	 public void export(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("orders",new OrderBean());
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/x-download");
	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"ReceiveOrderReport.pdf\""));

	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.exportPdfFile(orders);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 }
	
	 @RequestMapping(value = "/approveexport", method = RequestMethod.POST)
	 public void ApproveOrderPdfExport(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("orders",new OrderBean());
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/x-download");
	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"ApproveOrderReport.pdf\""));
	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.ApproveOrderReport(orders);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 }
	 
	 @RequestMapping(value = "/pendingexport", method = RequestMethod.POST)
	 public void pendingOrderPdfExport(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("orders",new OrderBean());
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/x-download");
	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"PendingOrderReport.pdf\""));
	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.PendingOrderReport(orders);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 }
	
	 @RequestMapping(value = "/exportexcel", method = RequestMethod.POST)
	 public void exportExcel(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	  response.setHeader("Content-disposition", "attachment; filename=report.xlsx");

	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.exportPdfFile(orders);
	  JRXlsxExporter exporter = new JRXlsxExporter();
	  exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	  exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, out);
      exporter.exportReport();
      out.flush();
      out.close();	 
	 }
	 
	 @RequestMapping(value = "/ordersummaryexport", method = RequestMethod.POST)
	 public void OrderSummaryPdfExport(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("orders",new OrderBean());
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/x-download");
	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"Orders_SummaryReport.pdf\""));
	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.OrderSummaryReport(orders);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 }
	 
	 @RequestMapping(value = "/userhistoryexport", method = RequestMethod.POST)
	 public void userOrderHistoryPdfExport(ModelAndView model, HttpServletResponse response, @Valid OrderBean orders) throws IOException, JRException, SQLException {
	  JasperPrint jasperPrint = null;
	  model.addObject("orders",new OrderBean());
	  model.addObject("auth", getUser());
	  model.addObject("control", getUser().getRole().getRole());
	  response.setContentType("application/x-download");
	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"user_histroy_Report.pdf\""));
	  OutputStream out = response.getOutputStream();
	  jasperPrint = jasperService.UserHistoryReport(orders);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 }
	
	/*
	 * @RequestMapping(value = "/download", method = RequestMethod.POST) public
	 * ModelAndView downloadFile(Model model, @Valid UserDetail userDetail) {
	 * 
	 * 
	 * ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.addObject("userDetail",new UserDetail());
	 * 
	 * 
	 * try {
	 * 
	 * if (userDetail != null && userDetail.getImagePath() != null) //
	 * jasperService.downloadReportFile(userDetail.getImagePath()); else {
	 * model.addAttribute("msgdanger", "Failed to generate QR code!!"); //
	 * model.addAttribute("isDisabled", Boolean.FALSE); }
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); System.out.println(e); } modelAndView.addObject("users",
	 * userService.findAll()); modelAndView.addObject("auth", getUser());
	 * modelAndView.addObject("control", getUser().getRole().getRole());
	 * modelAndView.setViewName("report"); return modelAndView; }
	 */
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user =(User) userService.findUserByEmail(auth.getName());
		return user;
	}

}
