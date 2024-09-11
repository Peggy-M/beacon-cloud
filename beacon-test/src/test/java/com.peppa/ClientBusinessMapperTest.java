package com.peppa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peppa.test.client.CacheClient;
import com.peppa.test.entity.ClientBusiness;
import com.peppa.test.mapper.ClientBusinessMapper;
import com.peppa.test.TestStarterApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest(classes = TestStarterApp.class)
@RunWith(SpringRunner.class)
public class ClientBusinessMapperTest {

    @Autowired
    private ClientBusinessMapper mapper;

    @Autowired
    private CacheClient cacheClient;

    @Test
    public void findById() throws JsonProcessingException {
        ClientBusiness cb = mapper.findById(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(objectMapper.writeValueAsString(cb), Map.class);
        cacheClient.hmset("client_business:" + cb.getApikey(),map);
    }
}