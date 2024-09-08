package com.peppa.api.server.impl;

import com.peppa.api.filter.CheckFilterContext;
import com.peppa.api.form.SingleSendForm;
import com.peppa.api.server.SmsServer;
import com.peppa.common.core.domain.AjaxResult;
import com.peppa.common.core.enums.ExceptionEnums;
import com.peppa.common.core.model.StandardSubmit;
import com.peppa.common.core.utils.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: peppa
 * @Description: 客户短信校验服务业务具体的实现类
 * @Date: Created in 9:34 2024/8/28
 */
@Service
@Slf4j
public class SmsServerImpl implements SmsServer {

    /**
     * 客户端IP地址的请求头信息，多个用','隔开。
     */
    @Value("${headers}")
    private String headers;

    /**
     * 基于请求头获取信息时，可能获取到的未知信息
     */
    private final String UNKNOW = "unknow";

    /**
     * 如果是当前请求头获取IP地址，需要截取到第一个','未知
     */
    private final String X_FORWARDED_FOR = "x-forwarded-for";

    @Autowired
    private CheckFilterContext checkFilterContext;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public AjaxResult singleSend(SingleSendForm singleSendForm, BindingResult bindingResult, HttpServletRequest req) {
        //@Validated 校验之后会将校验的结果放到 bindingResult 当中
        //1. 校验参数
        if (bindingResult.hasErrors()){
            String msg = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            log.info("【接口模块-单条短信Controller】 参数不合法 msg = {}",msg);
            return AjaxResult.error(ExceptionEnums.PARAMETER_ERROR.getCode(),msg);
        }
        //=========================获取真实的IP地址=========================================
        String ip = this.getRealIP(req);

        //=========================构建StandardSubmit，各种封装校验==========================
        StandardSubmit submit = new StandardSubmit();
        submit.setRealIP(ip);
        submit.setApikey(singleSendForm.getApikey());
        submit.setMobile(singleSendForm.getMobile());
        submit.setText(singleSendForm.getText());
        submit.setState(singleSendForm.getState());
        submit.setUid(singleSendForm.getUid());

        //=========================调用策略模式的校验链========================================
        checkFilterContext.check(submit);

        //========================基于雪花算法生成唯一id，并添加到StandardSubmit对象中，并设置发送时间=========================================
        submit.setSequenceId(snowFlakeUtil.nextId());
        submit.setSendTime(LocalDateTime.now());
        return null;
    }


    /**
     * 获取客户端真实的IP地址
     * @param req 请求头信息
     * @return 获取的请求头的 ip 地址
     */
    private String getRealIP(HttpServletRequest req) {
        //1. 声明返回的ip地址
        String ip;

        //2. 遍历请求头，并且通过req获取ip地址
        for (String header : headers.split(",")) {
            // 健壮性校验
            if (!StringUtils.isEmpty(header)) {
                // 基于req获取ip地址
                ip = req.getHeader(header);
                // 如果获取到的ip不为null，不为空串，并且不为unknown，就可以返回
                if (!StringUtils.isEmpty(ip) && !UNKNOW.equalsIgnoreCase(ip)) {
                    // 判断请求头是否是x-forwarded-for
                    if (X_FORWARDED_FOR.equalsIgnoreCase(header) && ip.indexOf(",") > 0) {
                        ip = ip.substring(0,ip.indexOf(","));
                    }
                    // 返回IP地址
                    return ip;
                }
            }
        }

        //3. 如果请求头都没有获取到IP地址，直接基于传统的方式获取一个IP
        return req.getRemoteAddr();
    }
}
