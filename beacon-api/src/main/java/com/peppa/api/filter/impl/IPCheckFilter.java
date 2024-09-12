package com.peppa.api.filter.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.peppa.api.clinet.BeaconCacheClient;
import com.peppa.api.filter.CheckFilter;
import com.peppa.common.core.constant.CacheConstant;
import com.peppa.common.core.enums.ExceptionEnums;
import com.peppa.common.core.exception.ApiException;
import com.peppa.common.core.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: peppa
 * @Description: 校验请求的ip地址是否是白名单
 * @Date: Created in 23:11 2024/8/27
 */
@Service(value="ip")
@Slf4j
public class IPCheckFilter implements CheckFilter {

    @Autowired
    private BeaconCacheClient cacheClient;

    private final String IP_ADDRESS = "ipAddress";


    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验ip】   校验ing…………");
        //1. 根据CacheClient根据客户的apikey以及ipAddress去查询客户的IP白名单
        List<String> ip = (List<String>) cacheClient.hget(CacheConstant.CLIENT_BUSINESS + submit.getApikey(), IP_ADDRESS);
        submit.setIp(ip);

        //2. 如果IP白名单为null，直接放行，或者包含，修改逻辑判断。
        // 如果客户未设置IP白名单，认为客户认为什么IP都可以访问
        // 如果设置了IP白名单，才需要去做一个校验
        if(CollectionUtils.isEmpty(ip)|| ip.contains(submit.getRealIP())){
            log.info("【接口模块-校验ip】  客户端请求IP合法！");
            return;
        }

        //3. IP白名单不为空，并且客户端请求不在IP报名单内
        log.info("【接口模块-校验ip】  请求的ip不在白名单内");
        throw new ApiException(ExceptionEnums.IP_NOT_WHITE);
    }
}
