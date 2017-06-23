package main.dao;

import main.entity.User;

/**
 * 
 * @author lucio.li
 * ע��Ľӿ���
 */
public interface RegisterDao {
	
	void save(User user);
	User findByPhone(String phone);
	void update(User user);
	
}
