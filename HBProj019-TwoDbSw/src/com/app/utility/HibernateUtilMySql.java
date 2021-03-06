package com.app.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilMySql {
	private static SessionFactory factory;
	static {
		Configuration cfg=null;
		try {
			cfg=new Configuration().configure("/com/app/cfgs/hibernateMySql.cfg.xml");
			if(cfg!=null) {
				factory=cfg.buildSessionFactory();
			}
		} catch(HibernateException he) {
			he.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//static
	//get session object
	public static Session getSession() {
		Session session=null;
		if(factory!=null) {
			session=factory.openSession();
		}
		return session;
	}
	//close session object
	public static void closeSession(Session session) {
		if(session!=null)
				session.close();
	}
	//close session factory object
	public static void closeSessionFactory() {
		if(factory!=null)
				factory.close();
	}
}
