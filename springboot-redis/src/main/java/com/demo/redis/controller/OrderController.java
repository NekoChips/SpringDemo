package com.demo.redis.controller;

import com.demo.redis.annotation.TokenAccess;
import com.demo.redis.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 下单
     * @param request
     * @return
     */
    @RequestMapping("place")
    @TokenAccess
    public String ordering(HttpServletRequest request) {
        orderService.ordering(request);
        return "success.";
    }
}
