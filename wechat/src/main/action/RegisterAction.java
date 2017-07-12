package main.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import main.entity.User;
import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册相关的action都在这里
 */
public class RegisterAction extends ActionSupport{
	private File file;
	// 文件名
	private String fileFileName;
	//文件类型(MIME)
	private String fileContentType;
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String file1FileName) {
		this.fileFileName = file1FileName;
	}
	public void setFileContentType(String file1ContentType) {
		this.fileContentType = file1ContentType;
	}
	
	// IOC容器注入
	private RegisterService registerService;
	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}

	/**
	 * 注册的action
	 */
	public void register() throws IOException {
		System.out.println("开始注册");
		// 获取request和response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);


		User user = new User();

		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));

		//调用service
		String result = registerService.register(user, file, fileFileName);

		PrintWriter out = response.getWriter();
		//返回数据
		out.write(result);


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
		String code = request.getParameter("code");

		//调用service
		String result = registerService.checkRegisterCode(phone, code);
		PrintWriter out = response.getWriter();
		//返回数据
		out.write(result);


		//return null;
	}
	
}
