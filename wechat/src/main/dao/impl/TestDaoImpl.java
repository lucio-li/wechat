package main.dao.impl;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;



public class TestDaoImpl {
	// 注入SessionFactory对象
		private static SessionFactory sessionFactory;
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		public static void main(String[] arg) {
			findById();
		}
		
		
		@Test
		public static void findById() {
			
			
			
			
			Session session = null;
			Transaction tx = null;
			try {
				
				System.out.println("sdfa" + (sessionFactory == null));
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				SQLQuery sqlQuery = session.createSQLQuery("select * from test");
				// 注意：参数索引从0开始
				
				// 执行查询
				//q.list();
				System.out.println("test:" + sqlQuery.list());
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}
}
