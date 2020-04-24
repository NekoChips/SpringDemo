package com.demo.elastic.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NekoChips
 * @description ElasticProperties
 * @date 2020/4/22
 */
@Component
@ConfigurationProperties(prefix = "elastic")
public class ElasticProperties {

    private List<EsNode> nodes;

    private String username;

    private String password;

    private String schema = "http";

    public static class EsNode {

        /**
         * es 节点名称
         */
        private String name;

        /**
         * 地址
         */
        private String host;
        
        /**
         * 端口
         */
        private int port;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public List<EsNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<EsNode> nodes) {
        this.nodes = nodes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
