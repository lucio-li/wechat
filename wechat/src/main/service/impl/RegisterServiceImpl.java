package main.service.impl;


import main.dao.RegisterDao;
import main.dao.impl.RegisterDaoImpl;
import main.entity.User;
import main.service.RegisterService;
import main.util.MailUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;


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

	@Override
	public String register(User user, File file, String fileFileName) {
		String filePath = null;
		JSONObject jsonObject = new JSONObject();
		if (file != null) {
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			File destPath = new File(path);
			if (!destPath.exists()) {
				destPath.mkdir();
			}
			File destFile = new File(path, fileFileName);
			try {
				FileUtils.copyFile(file, destFile);
				filePath = destFile.getAbsolutePath();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		user.setHead_img(filePath);


		String phone = user.getPhone();
		if (phone == null || phone.trim().equals("")) {
			jsonObject.put("state", "failure");
			jsonObject.put("msg", "phone can't be null");
			return jsonObject.toString();//参数错误
		}
		String code = null;
		User userMsg = (User) registerDao.findByPhone(phone);

		//第一次注册，数据库还没数据
		if (userMsg == null) {
			code = createRandom();//生成随机的验证码
			user.setIdentify_code(code);
			try {
				MailUtils.sendEmail(phone + "@163.com", code);//使用163邮箱暂替手机验证码
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("state", "failure");
				jsonObject.put("msg", "email address error");
				return jsonObject.toString();//发送邮件失败，可能是邮箱地址不存在
			}
			registerDao.save(user);
			jsonObject.put("state", "success");
			return jsonObject.toString();
		} else {
			//再次注册，如果没注册成功
			if (userMsg.getStatus() == 0) {
				code = createRandom();
				userMsg.setIdentify_code(code);
				userMsg.setUsername(user.getUsername());
				userMsg.setPassword(user.getPassword());
				userMsg.setHead_img(user.getHead_img());
				userMsg.setPhone(user.getPhone());
				try {
					MailUtils.sendEmail(phone + "@163.com", code);//使用163邮箱暂替手机验证码

				} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("state", "failure");
					jsonObject.put("msg", "email address error");
					return jsonObject.toString();//发送邮件失败，可能是邮箱地址不存在
				}
				registerDao.update(userMsg);//更新数据库的验证码
				jsonObject.put("state", "success");
				return jsonObject.toString();
			} else {
				//手机号已经注册成功
				jsonObject.put("state", "failure");
				jsonObject.put("msg", "user already exists");
				return jsonObject.toString();
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
