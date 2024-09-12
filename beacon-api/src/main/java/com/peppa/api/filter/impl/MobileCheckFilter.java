package com.peppa.api.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.peppa.api.filter.CheckFilter;
import com.peppa.common.core.enums.ExceptionEnums;
import com.peppa.common.core.exception.ApiException;
import com.peppa.common.core.model.StandardSubmit;
import com.peppa.common.core.utils.PhoneFormatCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: peppa
 * @Description: 校验手机的号码的合法性
 * @Date: Created in 23:13 2024/8/27
 */
@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements CheckFilter {

    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验手机号】   校验ing…………");
        String mobile = submit.getMobile();
        if(!StringUtils.isEmpty(mobile) && PhoneFormatCheckUtil.isChinaPhone(mobile)){
            // 如果校验进来，代表手机号么得问题
            log.info("【接口模块-校验手机号】   手机号格式合法 mobile = {}",mobile);
            return;
        }
        log.info("【接口模块-校验手机号】   手机号格式不正确 mobile = {}",mobile);
        throw new ApiException(ExceptionEnums.ERROR_MOBILE);
    }
}
