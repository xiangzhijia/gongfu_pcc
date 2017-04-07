package com.gongfu.web.user.rpc.client;

import com.gongfu.web.user.rpc.client.code.ClientCode;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by a on 2017/3/29.
 */
@FeignClient(value = "pcc-service" , fallback = PccClientHysTrix.class)
public interface PccClient {
    @RequestMapping(value = ClientCode.RPC_PCC_URL+"/test", method = RequestMethod.GET)
    String test(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") String b);
}
