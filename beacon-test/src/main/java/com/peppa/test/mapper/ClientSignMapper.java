package com.peppa.test.mapper;

import com.peppa.test.entity.ClientSign;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientSignMapper {

    @Select("select * from client_sign where client_id = #{clientId}")
    List<ClientSign> findByClientId(@Param("clientId")Long clientId);

}