package com.javanull.web.config;

import com.javanull.web.domain.User;


/**
 * Created by lizhiming on 2018/3/14.
 */
public class UserUtil {

    private static User instance = null;



    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new User();
                }
            }
        }
        return instance;
    }


    public static void setInstance(WebConfig webConfig) {
        instance =new User();
        instance.setName(webConfig.getName());
    }
}
