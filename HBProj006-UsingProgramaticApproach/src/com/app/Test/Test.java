package com.app.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.app.domain.Employee;

public class Test {
	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory sf=null;
		Session ses=null;
		Transaction tx=null;
		//BootStrap hibernate
		cfg=new Configuration();
		//add configuration file
		cfg.configure("/com/app/cfgs/hibernate.cfg.xml");
		//add connection properties
		cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		cfg.setProperty("hibernate.connection.url", "jdbc:mysql:///test");
		cfg.setProperty("hibernate.connection.username", "root");
		cfg.setProperty("hibernate.connection.password", "root");
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.format_sql", "true");
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		//Add mapping resource file
		cfg.addFile("src/com/app/cfgs/employee.hbm.xml");
		//build session factory object
		sf=cfg.buildSessionFactory();
		//open session
		ses=sf.openSession();
		//create employee object
		Employee emp1=new Employee();
		//set the values to emp object
		emp1=ses.get(Employee.class, 102);
		//insert the data in db table if it is not exist
		if(emp1==null) {
			try {
				Employee emp=new Employee();
				emp.setEmpId(102); emp.setEmpName("Jitendra");emp.setEmpSal(22.22);emp.setEmpMail("jeet@gmail.com");
				tx=ses.beginTransaction();
				ses.save(emp);
				tx.commit();
				System.out.println("Data saved with unique id:"+emp.getEmpId());
			} catch(Exception e) {
				tx.rollback();
				System.out.println("object not found..");
				e.printStackTrace();
			}
		} else {
			System.out.println("Details for following id is already available...");
		}
		sf.close();
		//ses.close();
	}
}
