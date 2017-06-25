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
	// IOC容器注入
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}
	
	
	public String sendRegisterCode() throws IOException {
		
		// 获取request以及response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
 		
		//获取请求的手机号码
		String phone = request.getParameter("phone");
		
		//调用service
		String result = registerService.sendRegisterCode(phone);
		PrintWriter out = response.getWriter();
		//返回结果
		out.write(result);
		return null;
	}
	
}
