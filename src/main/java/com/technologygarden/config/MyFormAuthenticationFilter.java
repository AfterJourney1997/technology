package com.technologygarden.config;

import com.alibaba.fastjson.JSONObject;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义登录过滤器
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    public MyFormAuthenticationFilter() {
        super();
    }

    /**
     * 屏蔽OPTIONS请求
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 解决未登录302问题
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }

        } else {
            // 返回固定的JSON串
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
            WebUtils.toHttp(response).setStatus(403);
            WebUtils.toHttp(response).setHeader("Access-Control-Allow-Origin", "*");
            WebUtils.toHttp(response).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            WebUtils.toHttp(response).getWriter().print(JSONObject.toJSONString(new ResultBean<>(ResultStatus.NOT_LOGIN_ERROR)));
            return false;
        }
    }

}
