package com.demo.redis.aop;

import com.demo.redis.tool.RedisTool;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author NekoChips
 * @description 接口幂等性 切面
 * @date 2020/3/27
 */
@Aspect
@Component
public class IdempotenceAspect {

    private Logger logger = LoggerFactory.getLogger(IdempotenceAspect.class);

    private static final String PARAM_NAME = "request_token";

    private static final String CACHE_PREFIX = "cache:request:";

    @Autowired
    private RedisTool redisTool;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.demo.redis.annotation.Idempotence)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public void before(ProceedingJoinPoint joinPoint) {
        @SuppressWarnings("ConstantConditions") HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            String requestNo = checkRequest(request);
            // 查询缓存中是否有该 requestNo 的请求记录
            String existResult = redisTool.get(CACHE_PREFIX + requestNo);
            if (StringUtils.isEmpty(existResult)) {
                // 缓存中不存在该请求记录，说明请求已经被处理，不能重复发起同一个请求
                throw new Exception("Can not resubmit the same request!");
            }

            // 执行接口逻辑
            joinPoint.proceed();

            // 执行完毕后 删除缓存
            boolean delete = redisTool.delete(CACHE_PREFIX + requestNo);
            // 避免并发问题，做删除判断
            if (!delete) {
                throw new Exception("delete token failed");
            }

        } catch (Throwable throwable) {
            logger.error("IdempotenceAspect error.", throwable);
        }
    }

    /**
     * 检查 request 中是否存在 requestNo
     *
     * @param request 请求内容
     * @return requestNo 对应的值
     */
    private String checkRequest(HttpServletRequest request) throws Exception {
        String requestNo = request.getHeader(PARAM_NAME);
        requestNo = Optional.ofNullable(requestNo).orElse(request.getParameter(PARAM_NAME));
        if (StringUtils.isEmpty(requestNo)) {
            throw new Exception("Illegal request because of no request token");
        }
        return requestNo;
    }
}
