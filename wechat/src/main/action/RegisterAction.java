package main.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册相关的action都在这里
 */
public class RegisterAction extends ActionSupport{
	
	// IOC容器注入
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}

	/**
	 * 发送手机验证码的action
	 * @return
	 * @throws IOException
	 */
	public void sendRegisterCode() throws IOException {
		
		// 获取request和response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
 		
		//获取手机号码
		String phone = request.getParameter("phone");
		
		//调用service
		String result = registerService.sendRegisterCode(phone);
		PrintWriter out = response.getWriter();
		//返回数据
		out.write(result);


		//return null;
	}

	/**
	 * 注册时检查手机验证码是否正确
	 * @throws IOException
	 */
	public void checkRegisterCode() throws IOException {

		// 获取request和response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);

		//获取手机号码
		String phone = request.getParameter("phone");
		System.out.println("action Phone" + phone);
		String code = request.getParameter("code");

		//调用service
		String result = registerService.checkRegisterCode(phone, code);
		PrintWriter out = response.getWriter();
		//返回数据
		out.write(result);


		//return null;
	}
	
}
