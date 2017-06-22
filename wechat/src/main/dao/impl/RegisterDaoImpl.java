package main.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.dao.RegisterDao;
import main.entity.User;

public class RegisterDaoImpl implements RegisterDao{
	// ע��SessionFactory����
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(User user) {
		System.out.println("������֤�벢�������ݿ�");
		System.out.println("�ֻ���" + user.getPhone());
		user.setId(2);
		sessionFactory.getCurrentSession().save(user);
		
	}

}
