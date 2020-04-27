package com.demo.crypt.mapper;

import com.demo.crypt.CryptApplication;
import com.demo.crypt.bean.VipCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description VipCardMapper 测试类
 * @date 2020/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CryptApplication.class)
public class VipCardMapperTest {

    @Autowired
    private VipCardMapper vipCardMapper;
    
    @Test
    public void testSave() {
        VipCard vipCard = new VipCard();
        vipCard.setCardNo("100001");
        vipCard.setName("张三");
        vipCard.setIdNumber("110101199003070396");
        vipCard.setPhoneNumber("13412345678");

        vipCardMapper.saveOne(vipCard);
    }
    
    @Test
    public void testFindById() {
        System.out.println(vipCardMapper.findById(1));
    }
}
