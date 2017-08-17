package com.smart.security.realm;

import com.smart.security.Md5CredentialsMatcher;
import com.smart.security.SecurityConstant;
import com.smart.security.SmartSecurity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * Created by wm on 2017/8/16.
 */
public class SmartCustomRealm extends AuthorizingRealm {


    private final SmartSecurity smartSecurity;

    public SmartCustomRealm(SmartSecurity smartSecurity) {
        this.smartSecurity = smartSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());


    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证

        //获取用户名
        String userName = ((UsernamePasswordToken) authenticationToken).getUsername();
        //获取数据库中对应的密码
        String password = smartSecurity.getPassword(userName);

        //便于后续认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();

        simpleAuthenticationInfo.setPrincipals(new SimplePrincipalCollection(userName, super.getName()));

        simpleAuthenticationInfo.setCredentials(password);

        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
