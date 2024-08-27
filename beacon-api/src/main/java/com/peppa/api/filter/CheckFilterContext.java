package com.peppa.api.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: peppa
 * @Description: 获取容器注入的所有过滤器
 * @Date: Created in 23:21 2024/8/27
 */
@Component
@RefreshScope
public class CheckFilterContext {

    @Autowired
    private Map<String,CheckFilter> checkFilters;

    @Value("${filters:apikey,ip,sign,template}")
    private String filters;

    public void check(Object obj){
        String[] filterArray = filters.split(",");
        for (String filter : filterArray) {
            CheckFilter checkFilter = checkFilters.get(filter);
            checkFilter.check(obj);
        }
    }
}
