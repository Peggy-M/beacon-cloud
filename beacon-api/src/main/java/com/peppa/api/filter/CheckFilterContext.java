package com.peppa.api.filter;

import com.peppa.common.core.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: peppa
 * @Description: 获取容器注入的所有过滤器
 * @Date: Created in 23:21 2024/8/27
 */
@Component
@RefreshScope
public class CheckFilterContext {

    // Spring的IOC会将对象全部都放到Map集合中
    // 基于4.x中Spring提供的反省注解，基于Map只拿到需要的类型对象即可
    @Autowired
    private Map<String, CheckFilter> checkFiltersMap;

    @Value("${filters:apikey,ip,sign,template}")
    private String filters;

    /**
     * 当前check方法用于管理校验链的顺序
     */
    public void check(StandardSubmit submit) {
        // 单挑短信发送的策略
        //1. 将获取到filters基于,做切分
        String[] filterArray = filters.split(",");
        //2. 遍历数组即可
        for (String filter : filterArray) {
            CheckFilter checkFilter = checkFiltersMap.get(filter);
            checkFilter.check(submit);
        }
    }
}
