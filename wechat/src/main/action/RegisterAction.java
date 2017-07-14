package main.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import main.entity.User;
import main.service.RegisterService;
import main.service.impl.RegisterServiceImpl;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


		/**
 * 注册相关的action都在这里
 */
public class RegisterAction extends ActionSupport{
	private File file;
	// 文件名
	private String fileFileName;
	//文件类型(MIME)
	private String fileContentType;
	// IOC容器注入
	private RegisterService registerService;
	private Map<String, Object> dataMap;



	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String file1FileName) {
		this.fileFileName = file1FileName;
	}

	public void setFileContentType(String file1ContentType) {
		this.fileContentType = file1ContentType;
	}


	public void setRegisterService(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}



	/**
	 * 注册的action
	 */
	public String register() throws IOException {
		System.out.println("开始注册");
		// 获取request和response
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);


		User user = new User();

		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		System.out.println(request.getParameter("username"));
		//调用service
		String result = registerService.register(user, file, fileFileName);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();



		out.write(result);
		out.flush();
		out.close();
		return "success";

	}

	public static void main(String[] args) {
		JSONObject jsonObject=new JSONObject();
//		jsonObject.accumulate("user", user);
		jsonObject.put("user", "user");
		System.out.println(jsonObject.toString());
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
