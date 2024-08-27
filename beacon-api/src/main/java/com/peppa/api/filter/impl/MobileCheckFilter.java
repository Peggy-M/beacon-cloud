package com.peppa.api.filter.impl;

import com.peppa.api.filter.CheckFilter;
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
    public void check(Object obj) {
        log.info("[接口模块校验]--- 校验手机号的格式合法性");
    }
}
