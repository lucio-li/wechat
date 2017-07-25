package main.util;

/**
 * 放常亮的类，主要是JWT的一些常亮
 * Created by lucio.li on 2017/7/5.
 */
public class Constant {
    /**
     * jwt
     *
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final long JWT_TTL = 60*60*1000;  //millisecond
    public static final long JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final long JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}