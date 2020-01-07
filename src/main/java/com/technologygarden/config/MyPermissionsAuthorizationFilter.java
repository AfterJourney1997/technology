package com.technologygarden.config;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义权限验证过滤器
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    /**
     * 解决302
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        /*Subject subject = getSubject(request, response);
        if (subject.getPrincipal() != null) {
            return true;
        } else {
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
            WebUtils.toHttp(response).getWriter().print(JSONObject.toJSONString(ResultStatus.AUTH_EMPTY_ERROR.getMessage()));
        }*/
        return super.onAccessDenied(request, response);

    }

}
