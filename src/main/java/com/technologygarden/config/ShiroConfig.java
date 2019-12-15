package com.technologygarden.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
    @Bean
    MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        return new MySessionManager();
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/page/401", "anon");
        chain.addPathDefinition("/page/403", "anon");

        // swagger权限
        chain.addPathDefinition("/swagger-ui.html", "anon");
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/v2/**", "anon");
        chain.addPathDefinition("/swagger-resources/**", "anon");
        chain.addPathDefinition("/v2/api-docs", "anon");
        chain.addPathDefinition("/webjars/springfox-swagger-ui/**", "anon");

        // 管理员权限拦截
        Map<String, String> adminMap = new LinkedHashMap<>();
        adminMap.put("/room/garden/**", "perms[/room/garden]");
        adminMap.put("/room/company/**", "perms[/room/company]");
        adminMap.put("/asset/building/**", "perms[/asset/building]");
        adminMap.put("/asset/furniture/**", "perms[/asset/furniture]");
        adminMap.put("/asset/assetCount/**", "perms[/asset/assetCount]");
        adminMap.put("/service/policyRelated/**", "perms[/service/policyRelated]");
        adminMap.put("/service/conditionEnter/**", "perms[/service/conditionEnter]");
        adminMap.put("/Approval/**", "perms[/Approval]");
        adminMap.put("/service/activityIncubation/**", "perms[/service/activityIncubation]");
        adminMap.put("/consociation/**", "perms[/consociation]");
        adminMap.put("/notice/**", "perms[/notice]");
        adminMap.put("/system/activityCategory/**", "perms[/system/activityCategory]");
        adminMap.put("/system/jobTitle/**", "perms[/system/jobTitle]");
        adminMap.put("/system/propertyDevice/**", "perms[/system/propertyDevice]");
        adminMap.put("/politicsStatus/manage/**", "perms[/politicsStatus/manage]");

        // 退出
        adminMap.put("/logout", "logout");

        // 项目权限配置
        chain.addPathDefinitions(adminMap);

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");

        return chain;
    }

}
