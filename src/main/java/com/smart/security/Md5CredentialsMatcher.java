package com.smart.security;

import com.common.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * Created by wm on 2017/8/16.
 */
public class Md5CredentialsMatcher implements CredentialsMatcher {

    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {


        //用户输入密码
        String submit = String.valueOf(((UsernamePasswordToken) authenticationToken).getPassword());

        String encry = String.valueOf(authenticationInfo.getCredentials());

        return CodecUtil.md5(submit).equals(encry);


    }
}

