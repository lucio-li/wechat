package main.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lucio.li on 2017/6/27.
 * 读取properties文件的工具类
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties mailProps;

    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        //"开始加载properties文件内容......."
        logger.info("start to read pro");
        mailProps = new Properties();
        InputStream mailIn = null;
        try {
            //通过类加载器进行获取properties文件流
            mailIn = PropertyUtil.class.getClassLoader().getResourceAsStream("main/config/email.properties");
            mailProps.load(mailIn);
        } catch (FileNotFoundException e) {
            //System.out.println("properties文件未找到");
            logger.error("properties文件未找到");
        } catch (Exception e) {
            //System.out.println("出现IOException");
            logger.error("出现IOException");
            e.printStackTrace();
        } finally {
            try {
                if(null != mailIn) {
                    mailIn.close();
                }
            } catch (IOException e) {
                //logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        //logger.info("加载properties文件内容完成...........");
        //logger.info("properties文件内容：" + mailProps);
    }

    public static String getProperty(String key){
        if(null == mailProps) {
            System.out.println("加载失败");
            loadProps();
        }
        return mailProps.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == mailProps) {
            loadProps();
        }
        return mailProps.getProperty(key, defaultValue);
    }
}