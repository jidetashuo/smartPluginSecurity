package com.smart.security;

import com.common.ReflectionUtil;
import com.helper.ConfigHelper;

/**
 * Created by wm on 2017/8/16.
 */
public class SecurityConfig {


    public static String getRealms() {

        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static SmartSecurity getSmartSecurity() {

        String className = ConfigHelper.getString(SecurityConstant.SMART_CURITY);

        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return (SmartSecurity) ReflectionUtil.newInstance(cls);
    }


}
