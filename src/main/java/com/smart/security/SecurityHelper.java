package com.smart.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


/**
 * Created by wm on 2017/8/17.
 */
public class SecurityHelper {

    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    public static void login(String username, String password) {

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser != null) {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * 退出
     */
    public static void logout() {

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {

            currentUser.logout();
        }

    }


}
