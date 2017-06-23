package main.dao;

import main.entity.User;

/**
 * 
 * @author lucio.li
 * 注册的接口类
 */
public interface RegisterDao {
	
	void save(User user);
	User findByPhone(String phone);
	void update(User user);
	
}
