package com.gongfu;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class PccApplicationTests {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected MockHttpSession session;

    protected JSONObject clientHeader = new JSONObject();

    @Before
    public void beFor() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
}
