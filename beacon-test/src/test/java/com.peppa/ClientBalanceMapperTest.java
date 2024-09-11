package com.peppa;

import com.peppa.test.TestStarterApp;
import com.peppa.test.client.CacheClient;
import com.peppa.test.mapper.ClientBalanceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TestStarterApp.class)
@RunWith(SpringRunner.class)
public class ClientBalanceMapperTest {

    @Autowired
    private ClientBalanceMapper mapper;

    @Autowired
    private CacheClient cacheClient;

    @Test
    public void findByClientId() {
        Long balance = mapper.findByClientId(1L);
        System.out.println(balance);

        cacheClient.set("client_balance:1",balance);
    }
}