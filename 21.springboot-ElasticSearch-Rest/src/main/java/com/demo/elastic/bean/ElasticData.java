package com.demo.elastic.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description Elasticsearch Bean 封装类
 * @date 2020/4/23
 */
public class ElasticData<T> implements Serializable {

    private static final long serialVersionUID = -8429556593383199011L;
    
    private String id;

    private String type;

    private T data;

    public ElasticData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        this.type = this.data.getClass().getSimpleName();
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
