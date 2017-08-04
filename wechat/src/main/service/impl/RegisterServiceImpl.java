package main.service.impl;


import main.callable.EmailSender;
import main.callable.ImageUploader;
import main.dao.UserDao;
import main.dao.impl.UserDaoImpl;
import main.entity.User;
import main.service.RegisterService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import java.io.File;


/**
 * 
 * @author lucio.li
 * 注册的service类
 */
public class RegisterServiceImpl implements RegisterService{
	// IOC注入
	private UserDao userDao;
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	/**
	 *
	 * @param user
	 * @param file
	 * @param fileFileName
	 * @return
	 */
	@Override
	public String register(User user, File file, String fileFileName) {



		JSONObject jsonObject = new JSONObject();
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		String filePath = path + "/" + fileFileName;
		user.setHead_img(filePath);//头像路径



		String email = user.getEmail();
		if (email == null || email.trim().equals("")) {
			jsonObject.put("status", "failure");
			jsonObject.put("msg", "email can't be null");
			return jsonObject.toString();//参数错误
		}
		User userMsg = (User) userDao.findByEmail(email);

		//没注册，数据库还没数据
		if (userMsg == null) {
			jsonObject.put("status", "failure");
			jsonObject.put("msg", "user don't exist");
			return jsonObject.toString();
		} else {
			//已注册，上传头像
			File oldImg = new File(userMsg.getHead_img());
			if (oldImg.exists()) {
				oldImg.delete();
			}
			ImageUploader imageUploader = new ImageUploader(file, fileFileName, path);
			imageUploader.start();//文件上传多线程
			userMsg.setHead_img(user.getHead_img());

			userDao.update(userMsg);//更新数据库的头像url
			jsonObject.put("status", "success");
			return jsonObject.toString();

		}


	}

	/**
	 *	注册的操作
	 * @param user
	 * @return
	 */
	@Override
	public String register(User user) {
		JSONObject jsonObject = new JSONObject();
		String email = user.getEmail();
		if (email == null || email.trim().equals("")) {
			jsonObject.put("status", "failure");
			jsonObject.put("msg", "email can't be null");
			return jsonObject.toString();//参数错误
		}
		String code = null;
		User userMsg = (User) userDao.findByEmail(email);

		//第一次注册，数据库还没数据
		if (userMsg == null) {
			code = createRandom();//生成随机的验证码
			user.setIdentify_code(code);

			EmailSender emailSender = new EmailSender(email, code);
			emailSender.start();//多线程发送邮件验证码
			userDao.save(user);
			jsonObject.put("status", "success");
			return jsonObject.toString();
		} else {
			//再次注册，如果没注册成功
			if (userMsg.getStatus() == 0) {
				code = createRandom();
				userMsg.setIdentify_code(code);
				userMsg.setUsername(user.getUsername());
				userMsg.setPassword(user.getPassword());
				userMsg.setEmail(user.getEmail());
				EmailSender emailSender = new EmailSender(email, code);
				emailSender.start();//发送邮件验证码
				userDao.update(userMsg);//更新数据库的验证码
				jsonObject.put("status", "success");
				return jsonObject.toString();
			} else {
				//邮箱已经注册成功
				jsonObject.put("status", "failure");
				jsonObject.put("msg", "user already exists");
				return jsonObject.toString();
			}
		}


	}

	/**
	 * 检查邮箱的验证码
	 * @param email 邮箱地址
	 * @param code
	 * @return
	 */
	@Override
	public String checkRegisterCode(String email, String code) {
		JSONObject jsonObject = new JSONObject();
		//参数错误，返回false
		if (email == null || code == null || code.length() != 4) {
			jsonObject.put("status", "failure");
			jsonObject.put("msg", "args error");
			return jsonObject.toString();
		}
		//调用dao，查询对应的user信息
		User user = (User) userDao.findByEmail(email);
		if (user == null || !code.equals(user.getIdentify_code())) {//验证码错误
			jsonObject.put("status", "false");
			jsonObject.put("msg", "code error");
			return jsonObject.toString();
		} else {
			user.setStatus(1);//注册成功，修改状态为1，已注册
			userDao.update(user);
			jsonObject.put("status", "success");
			return jsonObject.toString();
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
