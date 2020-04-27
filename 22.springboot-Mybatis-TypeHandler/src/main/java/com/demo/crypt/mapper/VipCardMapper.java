package com.demo.crypt.mapper;

import com.demo.crypt.bean.VipCard;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author NekoChips
 * @description VipCard 数据处理层
 * @date 2020/4/26
 */
@Mapper
public interface VipCardMapper {

    /**
     * 新增一个会员卡
     * @param vipCard 会员卡信息
     * @return 成功数目
     */
    int saveOne(VipCard vipCard);

    /**
     * 查询会员卡
     * @param id 会员卡Id
     * @return 会员卡
     */
    VipCard findById(Integer id);

    /**
     * 查询会员卡
     * @param cardNo 会员卡号
     * @return 会员卡
     */
    VipCard findByNo(String cardNo);

    /**
     * 查询会员卡
     * @param phoneNumber 电话号
     * @return 会员卡
     */
    VipCard findByPhone(String phoneNumber);
}
