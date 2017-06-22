package main.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.dao.RegisterDao;
import main.entity.User;

public class RegisterDaoImpl implements RegisterDao{
	// 注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(User user) {
		System.out.println("发送验证码并插入数据库");
		System.out.println("手机号" + user.getPhone());
		user.setId(2);
		sessionFactory.getCurrentSession().save(user);
		
	}

}
