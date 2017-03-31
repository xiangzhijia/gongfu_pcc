package com.gongfu.web.user.rpc.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by a on 2017/3/29.
 */
@FeignClient("pcc-service")
public interface PccClient {
    @RequestMapping(value = "/api/user/test", method = RequestMethod.GET)
    String test(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") String b);
}
