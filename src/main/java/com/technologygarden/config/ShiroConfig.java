package com.technologygarden.config;

import com.technologygarden.dao.RightsMapper;
import com.technologygarden.entity.Rights;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ShiroConfig {

    private final RightsMapper rightsMapper;

    @Autowired
    public ShiroConfig(RightsMapper rightsMapper) {
        this.rightsMapper = rightsMapper;
    }

    // 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
    @Bean
    MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        return new MySessionManager();
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        // 获取管理员的权限列表
        List<Rights> rightsList = rightsMapper.selectAll();
        Map<String, String> rights = rightsList.stream().collect(Collectors.toMap(Rights::getRUrl, (e) -> "authc,perms[" + e.getRPerms() + "]"));
        log.info("shiro配置初始化，获取管理员权限成功 ---> " + rights);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new MyFormAuthenticationFilter());
        filters.put("perms", new MyPermissionsAuthorizationFilter());
        filters.put("roles", new MyRolesAuthorizationFilter());

        //shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, String> right = new LinkedHashMap<>();

        //哪些请求可以匿名访问
        right.put("/login", "anon");
        right.put("/page/401", "anon");
        right.put("/page/403", "anon");
        right.put("/image/**", "anon");
        right.put("/downloadFile/**", "anon");
        right.put("/downZip/**", "anon");
        right.put("/index.html", "anon");

        // swagger权限
        right.put("/swagger-ui.html", "anon");
        right.put("/webjars/**", "anon");
        right.put("/v2/**", "anon");
        right.put("/swagger-resources/**", "anon");
        right.put("/v2/api-docs", "anon");
        right.put("/webjars/springfox-swagger-ui/**", "anon");

        for(Map.Entry<String, String> entry : rights.entrySet()){
            right.put(entry.getKey(), entry.getValue());
        }

        // 管理员首页拦截
        right.put("/adminHome/**", "authc,roles[admin]");

        // 企业权限拦截
        // 企业申请
        right.put("/company/information/company/**", "authc,roles[companyNoAgreed]");
        // 平台申请
        right.put("/application/plaform/**", "authc,roles[companyAgreed]");
        // 企业信息
        right.put("/company/information/information/**", "authc,roles[companyAgreed]");
        // 房间信息
        right.put("/enterprise/roomInfo/**", "authc,roles[companyAgreed]");
        // 员工管理
        right.put("/employee/manage/**", "authc,roles[companyAgreed]");
        // 奖项申报
        right.put("/declareAward/manage/**", "authc,roles[companyAgreed]");
        // 意见反馈
        right.put("/opinion/manage/**", "authc,roles[companyAgreed]");
        // 服务申报
        right.put("/serviceApplication/manage/**", "authc,roles[companyAgreed]");
        // 校企合作
        right.put("/cooperation/manage/**", "authc,roles[companyAgreed]");

        //除了以上的请求外，其它请求都需要登录
        right.put("/**", "authc");

        // 退出
//        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(right);

        return shiroFilterFactoryBean;
    }

}
