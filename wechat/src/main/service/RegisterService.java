package main.service;
/**
 * 
 * @author lucio.li
 *
 */
public interface RegisterService {
	/**
	 * 发送注册短信验证码
	 */
	void sendRegisterCode(String phoneNumber);
}
