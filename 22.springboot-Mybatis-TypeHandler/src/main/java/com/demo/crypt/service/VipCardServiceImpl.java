package com.demo.crypt.service;

import com.demo.crypt.bean.VipCard;
import com.demo.crypt.mapper.VipCardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NekoChips
 * @description VipCardService 实现类
 * @date 2020/4/26
 */
@Slf4j
@Service
public class VipCardServiceImpl implements VipCardService{

    @Autowired
    private VipCardMapper vipCardMapper;
    
    
    @Override
    public int saveOne(VipCard vipCard) {
        return vipCardMapper.saveOne(vipCard);
    }

    @Override
    public VipCard findById(Integer id) {
        return vipCardMapper.findById(id);
    }

    @Override
    public VipCard findByNo(String cardNo) {
        return vipCardMapper.findByNo(cardNo);
    }

    @Override
    public VipCard findByPhone(String phoneNumber) {
        return vipCardMapper.findByPhone(phoneNumber);
    }
}
