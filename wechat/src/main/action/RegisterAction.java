package main.action;

import com.opensymphony.xwork2.ActionSupport;

import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;

public class RegisterAction extends ActionSupport{
	// IOC����ע��
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}
	
	@Override
	public String execute() {
		System.out.println("����action");
		
		// ����Service
		registerService.sendRegisterCode("1234679");
		// ���浽request
		
		
		return "success";
	}
	
	
}
