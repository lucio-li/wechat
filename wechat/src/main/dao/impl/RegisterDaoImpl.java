package main.dao.impl;

import main.dao.RegisterDao;
import main.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class RegisterDaoImpl implements RegisterDao{
	// 注入sessionFactory
	private SessionFactory sessionFactory = null;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(User user) {
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
			// 添加限制
			criteria.add(Restrictions.eq("phone", phone));
			
			if (criteria.list().size() == 0) {

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
			sessionFactory.getCurrentSession().flush();//update要flush才能更新
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
