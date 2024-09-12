package com.peppa.common.core.constant;

/**
 * 通用的一些常量
 * @author zjw
 * @description
 */
public interface SmsConstant {

    /**
     * 短信发送成功
     */
    int REPORT_SUCCESS = 1;

    /**
     * 短信发送失败
     */
    int REPORT_FAIL = 2;

    /**
     * 验证码类型短信
     */
    int CODE_TYPE = 0;

    /**
     * 通知类型短信
     */
    int NOTIFY_TYPE = 1;

    /**
     * 营销类型短信
     */
    int MARKETING_TYPE = 2;

}
