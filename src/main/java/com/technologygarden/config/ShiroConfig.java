package com.technologygarden.config;

import com.technologygarden.dao.RightsMapper;
import com.technologygarden.entity.Rights;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        List<Rights> rightsList = rightsMapper.selectAll();

        // 获取管理员的权限列表
        Map<String, String> rights = rightsList.stream().collect(Collectors.toMap(Rights::getRUrl, (e) -> "perms[" + e.getRPerms() + "]"));
        log.info("shiro配置初始化，获取管理员权限成功 ---> " + rights);

        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();

        //哪些请求可以匿名访问
        rights.put("/login", "anon");
        rights.put("/page/401", "anon");
        rights.put("/page/403", "anon");

        // swagger权限
        rights.put("/swagger-ui.html", "anon");
        rights.put("/webjars/**", "anon");
        rights.put("/v2/**", "anon");
        rights.put("/swagger-resources/**", "anon");
        rights.put("/v2/api-docs", "anon");
        rights.put("/webjars/springfox-swagger-ui/**", "anon");

        // 企业权限拦截
        rights.put("/enterprise/company/**", "roles[companyNoAgreed]");
        // 平台申请
        rights.put("/application/plaform/**", "roles[companyAgreed]");
        // 企业信息
        rights.put("/enterprise/information/**", "roles[companyAgreed]");
        // 房间信息
        rights.put("/enterprise/roomInfo/**", "roles[companyAgreed]");
        // 员工管理
        rights.put("/emloyee/manage/**", "roles[companyAgreed]");
        // 奖项申报
        rights.put("/declareAward/manage/**", "roles[companyAgreed]");
        // 意见反馈
        rights.put("/opinion/manage/**", "roles[companyAgreed]");
        // 服务申报
        rights.put("/seviceApplicaiton/manage/**", "roles[companyAgreed]");
        // 校企合作
        rights.put("/coperation/manage/**", "roles[companyAgreed]");

        // 退出
        rights.put("/logout", "logout");

        // 项目权限配置
        chain.addPathDefinitions(rights);

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");

        return chain;
    }

}
