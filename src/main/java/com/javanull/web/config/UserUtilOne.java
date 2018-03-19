package com.javanull.web.config;

import com.javanull.web.domain.User;


/**
 * Created by lizhiming on 2018/3/14.
 */
public class UserUtilOne {

    private static User instance = null;


    public static User get(WebConfig webConfig) {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new User();
                    instance.setName(webConfig.getName());
                }
            }
        }
        return instance;
    }


}
