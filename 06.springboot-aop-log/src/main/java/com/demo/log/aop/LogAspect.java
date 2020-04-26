package com.demo.log.aop;

import com.demo.log.annotation.RecordLog;
import com.demo.log.bean.SysLog;
import com.demo.log.bean.SysUser;
import com.demo.log.service.ISysLogService;
import com.demo.log.utils.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author NekoChips
 * @description 日志切面
 * @date 2020/3/26
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.demo.log.annotation.RecordLog)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        long beginMills = System.currentTimeMillis();
        try {
            // 执行方法
            Object obj = joinPoint.proceed();
            long endMills = System.currentTimeMillis();
            Long time = endMills - beginMills;
            saveSysLog(joinPoint, time);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, Long time) {
        SysLog sysLog = new SysLog();

        // 获取用户名
        String username = getLoginUser(joinPoint).getUsername();

        // 获取IP地址
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = IPUtil.getIpAddress(request);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method methodInfo = methodSignature.getMethod();

        // 获取注解中的描述
        RecordLog recordLog = methodInfo.getAnnotation(RecordLog.class);
        String operation = recordLog.value();

        // 获取类名
        String className = joinPoint.getTarget().getClass().getName();

        // 获取方法名
        String methodName = methodInfo.getName();

        // 获取参数
        String params = getParams(joinPoint);

        sysLog.setUsername(username);
        sysLog.setIpAddress(ipAddress);
        sysLog.setOperation(operation);
        sysLog.setMethod(className + "." + methodName);
        sysLog.setParams(params);
        sysLog.setTime(time);

        sysLogService.record(sysLog);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @param joinPoint 切点
     * @return 用户信息
     */
    private SysUser getLoginUser(ProceedingJoinPoint joinPoint) {

        // 这里模仿一个用户，实际场景中，一般从session或者token中获取用户信息
        SysUser user = new SysUser();
        user.setUserId("001");
        user.setUsername("NekoChips");

        return user;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint 切点
     * @return 请求参数
     */
    private String getParams(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String params = null;
        if (parameterNames.length != 0 && args.length != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                stringBuilder.append(parameterNames[i]).append(" : ").append(args[i]);
                if (i < parameterNames.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            params = stringBuilder.toString();
        }
        return params;
    }
}
