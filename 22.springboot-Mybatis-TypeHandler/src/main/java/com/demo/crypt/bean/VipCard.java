package com.demo.crypt.bean;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author NekoChips
 * @description 会员卡信息
 * @date 2020/4/26
 */
@Data
public class VipCard implements Serializable {
    
    private static final long serialVersionUID = -4219484030832940945L;

    /**
     * id
     */
    private Integer id;

    /**
     * 会员卡号
     */
    @Size(min = 11, max = 11, message = "会员卡号长度必须为 11 位")
    private String cardNo;

    /**
     * 用户名
     */
    private String name;

    /**
     * 身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))(0[1-9]|([1|2][0-9])|3[0-1])((\\d{4})|\\d{3}X)$", message = "非法身份证号")
    private String idNumber;

    /**
     * 手机号
     */
    @Pattern(regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$", message = "非法手机号")
    private String phoneNumber;

}
