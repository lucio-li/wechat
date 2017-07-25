package main.dao;

import main.entity.User;

/**
 * Created by Lucio.li on 2017/7/24.
 */
public interface LoginDao {
    //邮箱加密码登录

    /**
     *
     * @param email
     * @param password
     * @return
     */
    User loginByPassword(String email, String password);
}
