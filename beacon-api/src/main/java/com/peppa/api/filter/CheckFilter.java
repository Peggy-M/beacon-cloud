package com.peppa.api.filter;

import com.peppa.common.core.model.StandardSubmit;

/**
 * @Author: peppa
 * @Description: 责任链策略模式的父接口
 * @Date: Created in 23:01 2024/8/27
 */
public interface CheckFilter {

    /**
     * 校验
     * @param submit 校验对象
     */
     void check(StandardSubmit submit);

}
