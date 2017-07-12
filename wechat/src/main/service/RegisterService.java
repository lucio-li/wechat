package main.service;

import main.entity.User;

import java.io.File;

/**
 * 
 * @author lucio.li
 *
 */
public interface RegisterService {
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	String register(User user, File file, String fileName);



	/**
	 * 注册检查验证码是否正确
	 * @param phoneNumber
	 * @return
	 */
	String checkRegisterCode(String phoneNumber, String code);
}
