package com.peppa.api.filter.impl;

import com.peppa.api.filter.CheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: peppa
 * @Description: 校验短信模板
 * @Date: Created in 23:15 2024/8/27
 */
@Service(value = "template")
@Slf4j
public class TemplateCheckFilter implements CheckFilter {
    @Override
    public void check(Object obj) {
        log.info("[接口模块校验]--- 校验短信的模板");
    }
}