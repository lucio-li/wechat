package main.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import main.service.LoginService;
import main.service.impl.LoginServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Lucio.li on 2017/7/24.
 */
public class LoginAction extends ActionSupport {

    private LoginService loginService;
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }
    @Override
    public String execute() throws Exception {
        // 获取request和response
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //获取参数
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //调用service
        String result = loginService.loginByPassword(email, password);

        out.write(result);
        out.flush();
        out.close();

        return "success";
    }


}
