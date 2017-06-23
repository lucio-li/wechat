package main.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.dao.RegisterDao;
import main.entity.User;

public class RegisterDaoImpl implements RegisterDao{
	// 注入SessionFactory对象
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
			// 构建条件
			criteria.add(Restrictions.eq("phone", phone));
			
			if (criteria.list().size() == 0) {
				System.out.println("没有该数据");
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
			sessionFactory.getCurrentSession().flush();//update要flush才能生效
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
