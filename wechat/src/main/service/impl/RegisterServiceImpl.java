package main.service.impl;


import main.dao.RegisterDao;
import main.dao.impl.RegisterDaoImpl;
import main.entity.User;
import main.service.RegisterService;
import main.util.MailUtils;


/**
 * 
 * @author lucio.li
 * 注册的service类
 */
public class RegisterServiceImpl implements RegisterService{
	// IOC注入
	private RegisterDao registerDao;
	public void setRegisterDao(RegisterDaoImpl registerDao) {
		this.registerDao = registerDao;
	}

	/**
	 * 向指定手机发送手机验证码
	 * @param phone
	 * @return 发送验证码成功失败
	 */
	@Override
	public String sendRegisterCode(String phone) {
		if (phone == null || phone.trim().equals("")) {
			return "failure";//参数错误
		}

		User user = (User) registerDao.findByPhone(phone);
		
		String code = null;
		//第一次注册，数据库还没数据
		if (user == null) {
			code = createRandom();//生成随机的验证码
			user = new User();
			user.setPhone(phone);
			user.setIdentify_code(code);
			try {
				MailUtils.sendEmail(phone + "@163.com", code);//使用163邮箱暂替手机验证码
			} catch (Exception e) {
				e.printStackTrace();
				return "sendEmailFalse";//发送邮件失败，可能是邮箱地址不存在
			}
			registerDao.save(user);
			return "success";
		} else {
			//再次获取手机验证码，如果没注册成功
			if (user.getStatus() == 0) {
				code = createRandom();
				user.setIdentify_code(code);
				
				try {
					MailUtils.sendEmail(phone + "@163.com", code);//使用163邮箱暂替手机验证码

				} catch (Exception e) {
					e.printStackTrace();
					return "sendEmailFalse";//发送邮件失败，可能是邮箱地址不存在
				}
				registerDao.update(user);//更新数据库的验证码
				return "success";
			} else {
				//手机号已经注册成功
				return "failure";
			}
		}
		
		
		
	}

	@Override
	public String checkRegisterCode(String phone, String code) {
		System.out.println(phone + code);
		//参数错误，返回false
		if (phone == null || code == null || code.length() != 4) {
			return "false";
		}
		//调用dao，查询对应的user信息
		User user = (User) registerDao.findByPhone(phone);
		if (user == null || !code.equals(user.getIdentify_code())) {//验证码错误
			return "false";
		} else {
			return "true";
		}

	}


	/**
	 * 产生四位验证码
	 * @return 4位数的验证码
	 */
	public String createRandom() {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			code.append((int)(Math.random() * 10));
		}
		return code.toString();
	}
	
}
