package main.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 读取配置文件的解密类
 * Created by lucio.li on 2017/7/5.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
    private String[] arrayProps = {"jdbc.user", "jdbc.password"};
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            return DESUtils.DK(propertyValue);
        }
        return propertyValue;
    }
    private boolean isEncryptProp(String propertyName) {
        for (String prop : arrayProps) {
            if (prop.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }


}
