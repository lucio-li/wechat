package main.dao;

import main.entity.User;

import java.util.List;

/**
 * Created by lucio.li on 2017/8/4.
 */
public interface UserDao {
    void save(User user);
    void delete(User user);
    User findByEmail(String email);
    void update(User user);
    List<User> list();
}
