package com.peppa.api.filter.impl;

import com.peppa.api.clinet.BeaconCacheClient;
import com.peppa.api.filter.CheckFilter;
import com.peppa.common.core.constant.ApiConstant;
import com.peppa.common.core.constant.CacheConstant;
import com.peppa.common.core.enums.ExceptionEnums;
import com.peppa.common.core.exception.ApiException;
import com.peppa.common.core.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: peppa
 * @Description: 校验客户的余额是否充足
 * @Date: Created in 23:16 2024/8/27
 */
@Service(value="fee")
@Slf4j
public class FeeCheckFilter implements CheckFilter {


    @Autowired
    private BeaconCacheClient cacheClient;

    /**
     * 只要短信内容的文字长度小于等于70个字，按照一条计算
     */
    private final int MAX_LENGTH = 70;

    /**
     * 如果短信内容的文字长度超过70，67字/条计算
     */
    private final int LOOP_LENGTH = 67;

    private final String BALANCE = "balance";


    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验客户余额】   校验ing…………");
        //1、从submit中获取到短信内容
        int length = submit.getText().length();

        //2、判断短信内容的长度，如果小于等于70，算作一条，如果大于70字，按照67字/条，算出来当前短信的费用
        if(length <= MAX_LENGTH){
            // 当前短信内容是一条
            submit.setFee(ApiConstant.SINGLE_FEE);
        }else{
            int strip = length % LOOP_LENGTH == 0 ? length / LOOP_LENGTH : length / LOOP_LENGTH + 1;
            submit.setFee(ApiConstant.SINGLE_FEE * strip);
        }

        //3、从Redis中查询出客户剩余的金额
        Long balance = ((Integer) cacheClient.hget(CacheConstant.CLIENT_BALANCE + submit.getClientId(), BALANCE)).longValue();

        //4、判断金额是否满足当前短信费用\
        if(balance >= submit.getFee()){
            log.info("【接口模块-校验客户余额】   用户金额充足！！");
            return;
        }

        //5、不满足就抛出异常
        log.info("【接口模块-校验客户余额】   客户余额不足");
        throw new ApiException(ExceptionEnums.BALANCE_NOT_ENOUGH);
    }

}