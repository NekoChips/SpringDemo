package com.demo.crypt.controller;

import com.demo.crypt.bean.VipCard;
import com.demo.crypt.service.VipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author NekoChips
 * @description VipCardController
 * @date 2020/4/26
 */
@Controller
@RequestMapping("vip")
public class VipCardController {

    @Autowired
    private VipCardService vipCardService;

    @PostMapping("card")
    public HttpEntity<?> save(@RequestBody VipCard vipCard) {
        int count = vipCardService.saveOne(vipCard);
        if (count > 0) {
            return ResponseEntity.ok(vipCard);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("card/{id}")
    public HttpEntity<?> findById(@PathVariable Integer id) {
        VipCard vipCard = vipCardService.findById(id);
        return ResponseEntity.ok(vipCard);
    }

    @GetMapping("card/cardNo/{cardNo}")
    public HttpEntity<?> findByNo(@PathVariable String cardNo) {
        VipCard vipCard = vipCardService.findByNo(cardNo);
        return ResponseEntity.ok(vipCard);
    }

    @GetMapping("card/phone/{phone}")
    public HttpEntity<?> findByPhone(@PathVariable String phone) {
        VipCard vipCard = vipCardService.findByPhone(phone);
        return ResponseEntity.ok(vipCard);
    }
    
}
