package com.demo.log.utils;


import com.alibaba.druid.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NekoChips
 * @description ip 工具类
 * @date 2020/3/26
 */
public class IPUtil {

    /**
     * 获取请求ip
     * @param request 请求
     * @return 发送请求的IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || StringUtils.equals("unknown", ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || StringUtils.equals("unknown", ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || StringUtils.equals("unknown", ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return StringUtils.equals("0:0:0:0:0:0:0:1", ipAddress) ? "127.0.0.1" : ipAddress;
    }
}
