package com.demo.sso.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yangjie.Chen
 * @description 订单controller
 * @date 2020/3/17
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("info")
    public String orderInfo() {
        return "this is an order information";
    }
}
