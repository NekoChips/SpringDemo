package com.demo.log.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 日志实体类
 * @date 2020/3/26
 */
public class SysLog implements Serializable {

    private static final long serialVersionUID = -751464683105237224L;

    /**
     * 日志编号
     */
    private Integer logNo;

    /**
     * 操作人
     */
    private String username;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 操作内容
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 操作耗时
     */
    private Long time;

    /**
     * 创建时间
     */
    private String createTime;

    public Integer getLogNo() {
        return logNo;
    }

    public void setLogNo(Integer logNo) {
        this.logNo = logNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "logNo=" + logNo +
                ", username='" + username + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", time=" + time +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
