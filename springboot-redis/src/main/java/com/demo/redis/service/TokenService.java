package com.demo.redis.service;

import com.demo.redis.core.RedisTool;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Token 相关
 */
@Aspect
@Component
public class TokenService {

    private static final String TOKEN_NAME = "token";

    private static final String TOKEN_PREFIX = "cache.request.token.";

    @Autowired
    private RedisTool redisTool;

    @Pointcut("@annotation(com.demo.redis.annotation.TokenAccess)")
    public void tokenAccess() {

    }

    public String createToken() {
        String token = String.valueOf(System.currentTimeMillis());
        StrBuilder cacheKey = new StrBuilder();
        cacheKey.append(TOKEN_PREFIX).append(token);

        redisTool.putString(cacheKey.toString(), token, null);

        return token;
    }

//    @Before("tokenAccess()")
//    public void checkToken(JoinPoint point) throws Exception {
//        Object[] args = point.getArgs();
//        HttpServletRequest request = (HttpServletRequest) args[0];
//        String token =
//                StringUtils.isEmpty(request.getHeader(TOKEN_NAME)) ?
//                        request.getHeader(TOKEN_NAME) : request.getHeader(TOKEN_NAME);
//
//        String existsToken = redisTool.getValue(TOKEN_PREFIX + token);
//        if (StringUtils.isEmpty(existsToken)) {
//            // redis 中不存在请求token，说明请求已经被执行过一次，不能重复执行
//            throw new Exception("can not resubmit the same request");
//        }
//    }
//
//    @After("tokenAccess()")
//    public void removeToken(JoinPoint joinPoint) throws Exception {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = (HttpServletRequest) args[0];
//        String token =
//                StringUtils.isEmpty(request.getHeader(TOKEN_NAME)) ?
//                        request.getHeader(TOKEN_NAME) : request.getHeader(TOKEN_NAME);
//
//        boolean deleted = redisTool.delete(TOKEN_PREFIX + token);
//        if (!deleted) {
//            throw new Exception("delete token failed");
//        }
//    }

    @Around("tokenAccess()")
    public void checkToken(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        try {
            String token = checkRequest(request);
            // 发起请求前先检查缓存中的token
            String existToken = redisTool.getValue(TOKEN_PREFIX + token);
            if (StringUtils.isEmpty(existToken)) {
                // 下单请求已经被处理了, 不能重复发起请求
                throw new Exception("can not resubmit the same request");
            }
            // 执行下单请求
            joinPoint.proceed();
            // 请求通过后清除缓存
            boolean deleted = redisTool.delete(TOKEN_PREFIX + token);
            // 为了避免并发问题，这里一定要做删除判断
            if (!deleted) {
                throw new Exception("delete cache failed");
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验请求合法性
     *
     * @param request 请求
     */
    protected String checkRequest(HttpServletRequest request) throws Exception {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            // 请求头部没有token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                // 请求参数中也没有token
                throw new Exception("Illegal request because of no token");
            }
        }
        return token;
    }
}
