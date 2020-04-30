package com.nekochips.kafka.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author NekoChips
 * @description 订单信息
 * @date 2020/4/30
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -6330218506336380533L;

    private Long id;

    private String orderNo;

    private List<ShopDetail> shopList;

    private Integer amount;
}
