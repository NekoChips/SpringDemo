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
    @Pattern(regexp = "(^/d{15}$)|(^/d{18}$)|(^/d{17}(/d|[A-Z])$)", message = "身份证号必须为15位或18位")
    private String idNumber;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])/d{8}$", message = "手机号必须满足规则")
    private String phoneNumber;

}
