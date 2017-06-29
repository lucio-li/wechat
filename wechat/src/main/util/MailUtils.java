package main.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lucio.li on 2017/6/27.
 */
public class MailUtils {

    private static String account;
    private static String password;//授权码
    private static String myEmailSMTPHost;//邮箱SMTP的地址
    private static Session session;
    static {
        init();
    }




    /**
     * 参数的初始化
     */
    public static void init() {
        account = PropertyUtil.getEmailProperty("account");
        account = DESUtils.DK(account);//解密
        password = PropertyUtil.getEmailProperty("password");
        password = DESUtils.DK(password);//解密
        myEmailSMTPHost = PropertyUtil.getEmailProperty("myEmailSMTPHost");
        myEmailSMTPHost = DESUtils.DK(myEmailSMTPHost);//解密
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        session = Session.getDefaultInstance(props);
        session.setDebug(false);                                 // 设置为true就是debug模式, 可以查看详细的发送 log



    }

    /**
     * 发送邮件
     * @param receiveMailAccount 收件人邮箱
     * @throws Exception
     */
    public static void sendEmail(String receiveMailAccount, String identifyCode) throws Exception {

        // 创建一封邮件
        MimeMessage message = createMimeMessage(account, receiveMailAccount, identifyCode);

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        transport.connect(account, password);

        //发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 关闭连接
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @param identifyCode 四位验证码
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(String sendMail, String receiveMail, String identifyCode) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "SEO", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "我", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("欢迎使用SEO的仿wechat应用", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        StringBuilder content = new StringBuilder("欢迎使用仿wechat应用，您此次的验证码是");
        content.append("<span style='color: green; font-size:20px;'>");
        content.append(identifyCode).append("</span>");
        content.append("。如果不是您本人操作，请忽略");
        message.setContent(content.toString(), "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
