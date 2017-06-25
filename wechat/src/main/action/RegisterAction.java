package main.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;

public class RegisterAction extends ActionSupport{
	// IOC����ע��
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}
	
	
	public String sendRegisterCode() throws IOException {
		
		// ��ȡrequest�Լ�response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
 		
		//��ȡ������ֻ�����
		String phone = request.getParameter("phone");
		
		//����service
		String result = registerService.sendRegisterCode(phone);
		PrintWriter out = response.getWriter();
		//���ؽ��
		out.write(result);
		return null;
	}
	
}
