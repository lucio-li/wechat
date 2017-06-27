package main.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.dao.RegisterDao;
import main.entity.User;

public class RegisterDaoImpl implements RegisterDao{
	// 注入sessionFactory
	private SessionFactory sessionFactory = null;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(User user) {
		System.out.println("进入save方法");
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public User findByPhone(String phone) {
		User user = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class);
			// ��������
			criteria.add(Restrictions.eq("phone", phone));
			
			if (criteria.list().size() == 0) {
				System.out.println("û�и�����");
				return null;
			} else {
				user = (User) criteria.list().get(0);
				return user;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			sessionFactory.getCurrentSession().flush();//updateҪflush������Ч
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
