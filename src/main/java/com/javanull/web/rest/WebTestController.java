package com.javanull.web.rest;

import com.javanull.web.domain.User;
import com.javanull.web.domain.UserRepository;
import com.javanull.web.test.RedisAndMysqlTest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lizhiming on 2018/3/13.
 */
@RestController
public class WebTestController {

    @Resource
    private RedisAndMysqlTest redisAndMysqlTest;


    @Resource
    private UserRepository userRepository;

    @GetMapping("/getkey")
    public String getkey(@RequestParam(defaultValue = "") String key) {
        String returnKey = redisAndMysqlTest.get(key);
        return StringUtils.isEmpty(returnKey) ? "null" : returnKey;
    }


    @GetMapping("/getUser")
    public List<User> getkey() {
        List<User> list = userRepository.findAll();
        return list;
    }
}
