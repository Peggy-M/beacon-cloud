package com.peppa;

import com.peppa.test.entity.ClientBusiness;
import com.peppa.test.mapper.ClientBusinessMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientBusinessMapperTest {

    @Autowired
    private ClientBusinessMapper mapper;

    @Test
    public void findById() {
        ClientBusiness cb = mapper.findById(1L);
        System.out.println(cb);
    }
}