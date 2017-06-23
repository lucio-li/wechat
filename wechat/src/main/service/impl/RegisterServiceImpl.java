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
	public String sendRegisterCode(String phone) {
		User user = (User) registerDao.findByPhone(phone);
		
		String code = null;
		//如果手机还没注册，就把手机号，验证码插入数据库，并返回验证码
		if (user == null) {
			code = createRandom();//随机产生4位验证码
			user = new User();
			user.setPhone(phone);
			user.setIdentify_code(code);
			registerDao.save(user);
			return code;
		} else {
			//数据库有这个手机号，但是还没注册成功
			if (user.getStatus() == 0) {
				code = createRandom();
				user.setIdentify_code(code);
				
				registerDao.update(user);//更新数据库的验证码
				return code;
			} else {
				//手机号已经注册了
				return "false";
			}
		}
		
		
		
	}
	
	
	//产生4位数的验证码，随机生成
	public String createRandom() {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			code.append((int)(Math.random() * 10));
		}
		System.out.println("产生的验证码是" + code.toString());
		return code.toString();
	}
	
}
