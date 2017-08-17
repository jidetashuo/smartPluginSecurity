package com.smart.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.omg.CORBA.Environment;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created by wm on 2017/8/16.
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> c, ServletContext servletContext) throws ServletException {

        //设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations","classpath:smart-security.ini");

        //注册listener

        servletContext.addListener(EnvironmentLoaderListener.class);

        //注册filter
        FilterRegistration.Dynamic smartSecurityFilter=servletContext.addFilter("SmartSecurityFilter",SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null,false,"/*");


    }
}
