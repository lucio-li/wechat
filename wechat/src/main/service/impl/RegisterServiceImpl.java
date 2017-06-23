package main.service.impl;


import main.dao.RegisterDao;
import main.dao.impl.RegisterDaoImpl;
import main.entity.User;
import main.service.RegisterService;


/**
 * 
 * @author lucio.li
 * ע���õ�service��
 */
public class RegisterServiceImpl implements RegisterService{
	// IOCע��
	private RegisterDao registerDao;
	public void setRegisterDao(RegisterDaoImpl registerDao) {
		this.registerDao = registerDao;
	}
	
	
	@Override
	public String sendRegisterCode(String phone) {
		User user = (User) registerDao.findByPhone(phone);
		
		String code = null;
		//����ֻ���ûע�ᣬ�Ͱ��ֻ��ţ���֤��������ݿ⣬��������֤��
		if (user == null) {
			code = createRandom();//�������4λ��֤��
			user = new User();
			user.setPhone(phone);
			user.setIdentify_code(code);
			registerDao.save(user);
			return code;
		} else {
			//���ݿ�������ֻ��ţ����ǻ�ûע��ɹ�
			if (user.getStatus() == 0) {
				code = createRandom();
				user.setIdentify_code(code);
				
				registerDao.update(user);//�������ݿ����֤��
				return code;
			} else {
				//�ֻ����Ѿ�ע����
				return "false";
			}
		}
		
		
		
	}
	
	
	//����4λ������֤�룬�������
	public String createRandom() {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			code.append((int)(Math.random() * 10));
		}
		System.out.println("��������֤����" + code.toString());
		return code.toString();
	}
	
}
