package com.peppa.api.filter.impl;

import com.peppa.api.filter.CheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: peppa
 * @Description: 校验请求的ip地址是否是白名单
 * @Date: Created in 23:11 2024/8/27
 */
@Service(value="ip")
@Slf4j
public class IPCheckFilter implements CheckFilter {
    @Override
    public void check(Object obj) {
        log.info("[接口模块校验]--- 校验请求的ip地址是否是白名单");
    }
}
