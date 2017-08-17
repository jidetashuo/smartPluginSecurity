package com.smart.security;

import com.smart.security.realm.SmartCustomRealm;
import com.smart.security.realm.SmartJdbcRealm;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by wm on 2017/8/16.
 */
public class SmartSecurityFilter extends ShiroFilter {


    @Override
    public void init() throws Exception {
        super.init();

        WebSecurityManager webSecurityManager = super.getSecurityManager();

        //设置多个realm

        setRealms(webSecurityManager);

        //设置cache

        setCache(webSecurityManager);

    }

    private void setCache(WebSecurityManager webSecurityManager) {


    }

    /**
     * 增加自定义realms
     *
     * @param realms
     */
    private void addCustomRealms(Set<Realm> realms) {

        SmartSecurity smartSecurity = SecurityConfig.getSmartSecurity();
        SmartCustomRealm smartCustomRealm = new SmartCustomRealm(smartSecurity);
        realms.add(smartCustomRealm);

    }

    /**
     * 增加JDBCrealms
     *
     * @param realms
     */
    private void addJdbcRealms(Set<Realm> realms) {

        SmartJdbcRealm smartJdbcRealm = new SmartJdbcRealm();
        realms.add(smartJdbcRealm);

    }

    private void setRealms(WebSecurityManager webSecurityManager) {

        String securityRealms = SecurityConfig.getRealms();

        if (securityRealms != null) {

            String[] sercurityArray = securityRealms.split(",");

            Set<Realm> realms = new LinkedHashSet<Realm>();

            for (String realm : sercurityArray) {

                if (realm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
                    addJdbcRealms(realms);
                } else if (realm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
                    addCustomRealms(realms);
                }
            }

            RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;

            realmSecurityManager.setRealms(realms);

        }
    }
}
