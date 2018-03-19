package com.javanull.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lizhiming on 2018/3/14.
 */
@Component
public class UserApplicationListener implements InitializingBean {

    @Autowired
    private WebConfig webConfig;



    @Override
    public void afterPropertiesSet() throws Exception {
        UserUtil.setInstance(webConfig);
    }
}
