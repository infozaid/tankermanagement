package com.sms.enterprise.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.sms.enterprise.beans.OrderBean;
import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BasicDataSource ds;
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public void save(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(order);

	}

	@Override
	public List<Order> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session
				.createQuery("from Order where finished=false and pendingrequest=false order by date_created ASC")
				.list();
		return orderList;
	}

	@Override
	public List<Order> findAllApproveOrder() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createQuery("from Order where finished=true order by date_created ASC").list();
		return orderList;
	}

	@Override
	public List<Order> findAllPendingOrder() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session
				.createQuery("from Order where pendingrequest=true and finished=false  order by date_created ASC")
				.list();
		return orderList;
	}

	@Override
	public List<Order> findHalfApproveOrder() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session
				.createQuery(
						"from Order where pendingrequest=true and finished=false and order_name = 'HALF WATER TANKER' ")
				.list();
		return orderList;
	}

	public List<Order> findByUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Order o where o.user=:user order by date_created DESC");
		query.setParameter("user", user);
		List list = query.list();
		return list;
	}

	public Long findOrderById(String unitno) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT  COUNT(*) as count FROM orders WHERE unitno=:unitno GROUP BY YEAR(date_created), MONTH(date_created) asc")
				.addScalar("count", LongType.INSTANCE);
		query.setParameter("unitno", unitno);
		Long count = (Long) query.setMaxResults(1).uniqueResult();
		return count;
	}

	public Long findOrderByDate(int user_id, String date_created) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT COUNT(*) as count FROM orders WHERE user_id=:user_id AND MONTH(date_created)=MONTH(:date_created)  AND  YEAR(date_created)= YEAR(:date_created) GROUP BY YEAR(date_created), MONTH(date_created)  asc")
				.addScalar("count", LongType.INSTANCE);
		query.setParameter("user_id", user_id);
		query.setParameter("date_created", date_created);
		Long count = (Long) query.setMaxResults(1).uniqueResult();
		return count;
	}

	public String findOrderByLastDate(int user_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT MAX(DATE_FORMAT(`date_created`, '%Y-%m-%d')) AS 'date'  FROM orders WHERE user_id=:user_id");
		query.setParameter("user_id", user_id);
		return (String) query.getSingleResult();
	}

	public int approveOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"update Order set message = 'your order will be deliver in 24  hours' , finished=true where id=:id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println(result);
		return result;
	}

	public int pendingOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"update Order set message = 'your order will be late  during some water supply reasons' ,pendingrequest=true where id=:id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println(result);
		return result;
	}

	public int halfapproveOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"update Order set message = 'we will deliver You a half water tanker for the cause of water shortage' , pendingrequest=true , order_name='HALF WATER TANKER' where id=:id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println(result);
		return result;
	}

	public JasperPrint ApproveOrderReport(OrderBean orders) throws SQLException, JRException, IOException {
		Connection conn = ds.getConnection();

		String path = resourceLoader.getResource("classpath:approveorder.jrxml").getURI().getPath();

		JasperReport jasperReport = JasperCompileManager.compileReport(path);

		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fromDate", orders.fromDate);
		parameters.put("toDate", orders.toDate);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

		return print;

	}

	@Override
	public List<Order> findPaginated() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createQuery("from Order where finished=false and pendingrequest=false order by date_created ASC").list();
		return orderList;
	}

	public Order searchAll(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = null;
		Criteria query = session.createCriteria(Order.class);
		query.add(Restrictions.like("unitno", keyword, MatchMode.START));
		query.add(Restrictions.like("phoneno", keyword, MatchMode.START));
		query.add(Restrictions.like("date_created", keyword, MatchMode.START));
		query.add(Restrictions.like("username", keyword, MatchMode.START));
		List<Order> entityList = query.list();
		if (!entityList.isEmpty()) {
			order = entityList.get(0);
		}
		return order;
	}

	@Override
	public List<Order> findBeforeDayOrder() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Order WHERE date_created < :date AND finished=FALSE ORDER BY date_created ASC");
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm");
		String dateString = df.format(new Date()).toString();
		cal.add(Calendar.HOUR, -36);
		Date date;

		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		date = cal.getTime();
		query.setParameter("date", date);
		List<Order> olist = query.list();
		return olist;
	}

	@Override
	public List<Order> searchOrder(String theSearchName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName

			// the "like" clause and the "%" wildcard characters,
			// allow us to search for substrings (ex. pat returns paterson, patel, ...)
			theQuery = session.createQuery(
					"FROM Order c WHERE unitno LIKE :theName or date_created LIKE :theName and finished=true order by date_created ASC", Order.class);
			theQuery.setParameter("theName", "%" + theSearchName + "%");

		} else {
			// theSearchName is empty, get all customers
			theQuery = session.createQuery("from Order where finished=true order by date_created ASC", Order.class);
		}

		// execute query and get result list
		List<Order> orders = theQuery.getResultList();

		return orders;
	}
	
	public List<Order> searchPendingOrder(String theSearchName){
		Session session = this.sessionFactory.getCurrentSession();
		Query theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName

			// the "like" clause and the "%" wildcard characters,
			// allow us to search for substrings (ex. pat returns paterson, patel, ...)
			theQuery = session.createQuery(
					"FROM Order c WHERE unitno LIKE :theName or date_created LIKE :theName and pendingrequest=true and finished=false  order by date_created ASC", Order.class);
			theQuery.setParameter("theName", "%" + theSearchName + "%");

		} else {
			// theSearchName is empty, get all customers
			theQuery = session.createQuery("from Order where pendingrequest=true and finished=false  order by date_created ASC", Order.class);
		}

		// execute query and get result list
		List<Order> orders = theQuery.getResultList();

		return orders;
	}
	
	public List<Order> searchRequestOrder(String theSearchName){
		Session session = this.sessionFactory.getCurrentSession();
		Query theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName

			// the "like" clause and the "%" wildcard characters,
			// allow us to search for substrings (ex. pat returns paterson, patel, ...)
			theQuery = session.createQuery(
					"FROM Order  WHERE unitno LIKE :theName or date_created LIKE :theName and finished=false and pendingrequest=false order by date_created ASC", Order.class);
			theQuery.setParameter("theName",theSearchName);

		} else {
			// theSearchName is empty, get all customers
			theQuery = session.createQuery("from Order where finished=false and pendingrequest=false order by date_created ASC", Order.class);
		}

		// execute query and get result list
		List<Order> orders = theQuery.getResultList();

		return orders;
	}

}
