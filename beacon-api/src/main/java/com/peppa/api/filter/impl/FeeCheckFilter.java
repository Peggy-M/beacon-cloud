package com.peppa.api.filter.impl;

import com.peppa.api.filter.CheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: peppa
 * @Description: 校验客户的余额是否充足
 * @Date: Created in 23:16 2024/8/27
 */
@Service(value="fee")
@Slf4j
public class FeeCheckFilter implements CheckFilter {

    @Override
    public void check(Object obj) {
        log.info("[接口模块校验]--- 校验客户剩余的金额是否充足");
    }
}