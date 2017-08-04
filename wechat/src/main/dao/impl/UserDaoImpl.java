package main.dao.impl;

import main.dao.UserDao;
import main.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by lucio.li on 2017/8/4.
 */
public class UserDaoImpl implements UserDao{
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
    public void delete(User user) {

    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class);
            // 添加限制
            criteria.add(Restrictions.eq("email", email));

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
        User userMsg = findByEmail(user.getEmail());
        if (userMsg == null) {

        }
        try {
            sessionFactory.getCurrentSession().update(user);
            sessionFactory.getCurrentSession().flush();//update要flush才能更新
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> list() {
        return null;
    }
}
