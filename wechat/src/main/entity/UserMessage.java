package main.entity;

/**
 * Created by Lucio.li on 2017/7/24.
 */
public class UserMessage {
    private int id;
    private String headImg;
    private String username;
    private String wechatNumber;
    private String twoBarCodes;
    private String address;
    private String sex;
    private String district;
    private String sign;
    private String qqNumber;
    public UserMessage(){};
    public UserMessage(int id, String headImg, String username, String wechatNumber, String twoBarCodes,
                       String address, String sex, String district, String sign, String qqNumber) {

        this.id = id;
        this.headImg = headImg;
        this.username = username;
        this.wechatNumber = wechatNumber;
        this.twoBarCodes = twoBarCodes;
        this.address = address;
        this.sex = sex;
        this.district = district;
        this.sign = sign;
        this.qqNumber = qqNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
