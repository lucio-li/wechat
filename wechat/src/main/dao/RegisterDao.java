package main.dao;

import main.entity.User;

/**
 * 
 * @author lucio.li
 * 注册的接口类
 */
public interface RegisterDao {
	/**
	 * 注册插入数据，随机产生验证码
	 */
	void save(User user);
	
}
