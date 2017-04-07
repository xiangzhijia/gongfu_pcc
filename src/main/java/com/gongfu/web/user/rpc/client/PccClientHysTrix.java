package com.gongfu.web.user.rpc.client;

import com.gongfu.web.user.rpc.client.code.ClientCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by a on 2017/4/7.
 */
@Component
public class PccClientHysTrix implements PccClient {
    @Override
    public String test(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") String b) {
        return ClientCode.RPC_PCC_ERROR;
    }
}
