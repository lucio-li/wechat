package main.dao.impl;

import main.dao.LoginDao;
import main.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Lucio.li on 2017/7/24.
 */
public class LoginDaoImpl implements LoginDao{
    // 注入sessionFactory
    private SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public User loginByPassword(String email, String password) {
        System.out.println("进入dao");
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class);
            // 添加限制
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password", password));

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

}
