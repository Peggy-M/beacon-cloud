package com.peppa.api.controller;

import com.peppa.api.form.SingleSendForm;
import com.peppa.api.server.SmsServer;
import com.peppa.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: peppa
 * @Description: 客户短信校验接口
 * @Date: Created in 0:38 2024/8/28
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    SmsServer smsServer;

    @PostMapping(value = "/singleSend", produces = "application/json;charset=utf-8")
    public AjaxResult singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult, HttpServletRequest request) {
        return smsServer.singleSend(singleSendForm, bindingResult, request);
    }

}
