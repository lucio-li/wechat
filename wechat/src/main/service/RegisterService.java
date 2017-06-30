package main.service;
/**
 * 
 * @author lucio.li
 *
 */
public interface RegisterService {
	/**
	 * 注册时发送
	 * @param phoneNumber
	 * @return
	 */
	String sendRegisterCode(String phoneNumber);

	/**
	 *
	 * @param phoneNumber
	 * @return
	 */
	String checkRegisterCode(String phoneNumber, String code);
}
