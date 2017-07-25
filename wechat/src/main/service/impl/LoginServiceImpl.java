package main.service.impl;

import main.dao.LoginDao;
import main.entity.User;
import main.service.LoginService;
import main.util.Constant;
import main.util.JWTUtils;
import net.sf.json.JSONObject;

/**
 * Created by Lucio.li on 2017/7/24.
 */
public class LoginServiceImpl implements LoginService{
    // IOC注入
    private LoginDao loginDao;

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public String loginByPassword(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("loginDao是" + (loginDao == null));
        User user = loginDao.loginByPassword(email, password);
        if (user == null) {//登录失败
            jsonObject.put("status", "failure");
            jsonObject.put("msg", "email or password wrong");
        } else {
            //登录成功，生成token
            String JWT_ID = Constant.JWT_ID;
            long JWT_TTL = Constant.JWT_TTL;
            String subject = JWTUtils.generalSubject(user);
            String token = JWTUtils.createJWT(JWT_ID, subject, JWT_TTL);

            System.out.println("token:" + token);

            jsonObject.put("status", "success");
            jsonObject.put("token", token);
            jsonObject.put("username", user.getUsername());
        }
        return jsonObject.toString();

    }
}
