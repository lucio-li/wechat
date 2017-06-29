package main.util;
/**
 * DES加密算法
 *
 * @author lucio.li
 */

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class DESUtils {
    private static String password = "SEOedcrf";

    private static SecretKey securekey;

    private static SecureRandom random;
    static {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");//windows默认的是SHA1PRNG
            DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));

            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            securekey = keyFactory.generateSecret(desKey);

            keyFactory = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * DES加密
     * @param str String 要加密的字符串
     * @return byte[]
     */
    public static String EK(String str) {
        Base64 base64 = new Base64();
        try{
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            byte[] result = cipher.doFinal(str.getBytes("UTF-8"));
            return base64.encodeAsString(result);//加密后的字符串
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密算法
     * @param str String
     * @return byte[]
     * @throws Exception
     */
    public static String DK(String str){
        Base64 base64 = new Base64();
        try {
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            byte[] result = cipher.doFinal(base64.decode(str));
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //测试
    public static void main(String args[]) throws UnsupportedEncodingException {

        //待加密内容
        String str = "smtp.163.com";
        String result = DESUtils.EK(str);

        System.out.println("加密后："+ result);

        //直接将如上内容解密
        try {
            String decryResult = DESUtils.DK( result);
            System.out.println("解密后："+ decryResult);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


}