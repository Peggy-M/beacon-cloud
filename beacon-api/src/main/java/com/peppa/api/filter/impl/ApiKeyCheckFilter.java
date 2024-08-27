package com.peppa.api.filter.impl;

import com.peppa.api.filter.CheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: peppa
 * @Description: 校验apikey
 * @Date: Created in 23:09 2024/8/27
 */
@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements CheckFilter {

    @Override
    public void check(Object obj) {
        log.info("[接口模块校验]--- 校验apikey");
    }
}
