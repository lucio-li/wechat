package main.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lucio.li on 2017/6/27.
 * 读取properties文件的工具类
 */
public class PropertyUtil {
    private static Properties mailProps;
    //private static Properties jdbcProps;
    static{
        mailProps = loadProps(mailProps, "main/config/email.properties");
    }

    synchronized static private Properties loadProps(Properties props, String fileUrl){
        //"开始加载properties文件内容......."

        props = new Properties();
        InputStream in = null;
        try {
            //通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(fileUrl);
            props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("properties文件未找到");

        } catch (IOException e) {
            System.out.println("出现IOException");

            //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("出现IOException");

            //e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                //logger.error("jdbc.properties文件流关闭出现异常");
                System.out.println("properties文件流关闭出现异常");
            }
        }
        //logger.info("加载properties文件内容完成...........");
        //logger.info("properties文件内容：" + mailProps);
        return props;
    }

    /**
     * 获取邮件服务器的属性
     * @param key
     * @return
     */
    public static String getEmailProperty(String key){
        if(null == mailProps) {
            mailProps = loadProps(mailProps, "main/config/email.properties");
        }
        return mailProps.getProperty(key);
    }

    /**
     * 获取邮件服务器的属性
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getEmailProperty(String key, String defaultValue) {
        if(null == mailProps) {
            mailProps = loadProps(mailProps, "main/config/email.properties");
        }
        return mailProps.getProperty(key, defaultValue);
    }
}