package main.callable;

import main.util.MailUtils;

/**
 * Created by Lucio.li on 2017/7/19.
 */
public class EmailSender extends Thread {
    private String email;
    private String code;
    public EmailSender(String email, String code) {
        this.email = email;
        this.code = code;
    }
    public void run(){
        System.out.println("email" + email);
        try {
            MailUtils.sendEmail(email, code);//使用163邮箱暂替手机验证码
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
