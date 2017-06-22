package main.action;

import com.opensymphony.xwork2.ActionSupport;

import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;

public class RegisterAction extends ActionSupport{
	// IOC容器注入
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}
	
	@Override
	public String execute() {
		System.out.println("访问action");
		
		// 调用Service
		registerService.sendRegisterCode("1234679");
		// 保存到request
		
		
		return "success";
	}
	
	
}
