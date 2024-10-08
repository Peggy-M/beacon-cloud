package com.peppa.api.filter.impl;

import com.peppa.api.clinet.BeaconCacheClient;
import com.peppa.api.filter.CheckFilter;
import com.peppa.common.core.constant.CacheConstant;
import com.peppa.common.core.enums.ExceptionEnums;
import com.peppa.common.core.exception.ApiException;
import com.peppa.common.core.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: peppa
 * @Description: 校验apikey
 * @Date: Created in 23:09 2024/8/27
 */
@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements CheckFilter {

    @Autowired
    private BeaconCacheClient cacheClient;


    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验apikey】   校验ing…………");
        //1. 基于cacheClient查询客户信息
        Map clientBusiness = cacheClient.hGetAll(CacheConstant.CLIENT_BUSINESS + submit.getApikey());

        //2. 如果为null，直接扔异常
        if(clientBusiness == null || clientBusiness.size() == 0){
            log.info("【接口模块-校验apikey】 非法的apikey = {}",submit.getApikey());
            throw new ApiException(ExceptionEnums.ERROR_APIKEY);
        }

        //3. 正常封装数据
        submit.setClientId(Long.parseLong(clientBusiness.get("id") + ""));
        log.info("【接口模块-校验apikey】 查询到客户信息 clientBusiness = {}",clientBusiness);
    }
}
