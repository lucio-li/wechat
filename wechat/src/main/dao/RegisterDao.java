package main.dao;

import main.entity.User;

/**
 * 
 * @author lucio.li
 * 注册的dao的接口类
 */
public interface RegisterDao {
	
	void save(User user);
	User findByEmail(String email);
	void update(User user);
	
}
