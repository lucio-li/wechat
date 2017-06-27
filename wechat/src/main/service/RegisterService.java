package main.service;
/**
 * 
 * @author lucio.li
 *
 */
public interface RegisterService {
	/**
	 * 注册的发送验证码
	 */
	String sendRegisterCode(String phoneNumber);
}
