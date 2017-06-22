package main.service.impl;


import main.dao.RegisterDao;
import main.dao.impl.RegisterDaoImpl;
import main.entity.User;
import main.service.RegisterService;


/**
 * 
 * @author lucio.li
 * 注册用的service类
 */
public class RegisterServiceImpl implements RegisterService{
	// IOC注入
	private RegisterDao registerDao;
	public void setRegisterDao(RegisterDaoImpl registerDao) {
		this.registerDao = registerDao;
	}
	
	@Override
	public void sendRegisterCode(String phone) {
		User user = new User();
		user.setPhone(phone);
		
		registerDao.save(user);
	}

}
