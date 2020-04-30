package com.nekochips.kafka.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 购物详情信息
 * @date 2020/4/30
 */
@Data
public class ShopDetail implements Serializable {

    private static final long serialVersionUID = 9199704931547822158L;

    private Long id;

    private String productNo;

    private String productName;

    private Integer price;

    private String buyNum;
}
