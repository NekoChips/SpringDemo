package com.nekochips.kafka.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 商品信息
 * @date 2020/4/30
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 5117824884735830001L;

    private Long id;

    private String productNo;
    
    private String name;

    private Integer price;
}
