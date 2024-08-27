package com.peppa.api.form;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @Author: peppa
 * @Description: 接受的手机验证信息
 * @Date: Created in 0:36 2024/8/28
 */
@Data
public class SingleSendForm {
    /** 客户的apikey */
    @NotBlank(message = "apikey不允许为空！")
    private String apikey;

    /** 手机号 */
    @NotBlank(message = "手机号不能为空！")
    private String mobile;


    /** 短信内容 */
    @NotBlank(message = "短信内容不能为空！")
    private String text;

    /** 客户业务内的uid */
    private String uid;

    /** 0-验证码短信 1-通知类短信 2-营销类短信 */
    @Range(min = 0,max = 2,message = "短信类型只能是0~2的整数！")
    @NotNull(message = "短信类型不允许非空！")
    private Integer state;
}
