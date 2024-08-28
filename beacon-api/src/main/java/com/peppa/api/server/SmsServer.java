package com.peppa.api.server;

import com.peppa.api.form.SingleSendForm;
import com.peppa.common.core.domain.AjaxResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: peppa
 * @Description: 客户短信校验服务业务
 * @Date: Created in 9:33 2024/8/28
 */
public interface SmsServer {

    AjaxResult singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult, HttpServletRequest req);
}
