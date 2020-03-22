package com.neko.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NekoChips
 * @description 自定义zuul认证过滤器
 * @date 2020/3/21
 */
public class AuthenticationZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(AuthenticationZuulFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String uri = request.getRequestURI();
        if (logger.isDebugEnabled()) {
            logger.debug("request uri: {}", uri);
        }

        return !StringUtils.equals(uri, "/auth/login");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // 判断请求头部中是否包含token信息
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header)) {
            // 请求头中没有，判断请求参数是否存在token信息
            header = request.getParameter("Authorization");
            if (StringUtils.isEmpty(header)) {
                // 请求中不包含token信息，提示登录
                currentContext.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                currentContext.setResponseBody("please login first!");
            }
        }
        // 向下游服务器传递token
        currentContext.addZuulRequestHeader("Authorization", header);

        return null;
    }

}
