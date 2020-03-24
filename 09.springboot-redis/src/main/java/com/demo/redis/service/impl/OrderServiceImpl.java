package com.demo.redis.service.impl;

import com.demo.redis.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements IOrderService {
    @Override
    public void ordering(HttpServletRequest request) {
        System.out.println("place an order");
    }
}
