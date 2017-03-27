package com.gongfu.web.user.service.message.impl;

import com.gongfu.base.BaseServiceImpl;
import com.gongfu.model.user.Message;
import com.gongfu.web.user.service.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by a on 2017/3/25.
 */
@Service
@Slf4j
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Async("oneAsync")
    @Override
    public Future<String> openThreadOne() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==================================");
        log.info("Execute method asynchronously" + Thread.currentThread().getName());
        return new AsyncResult<>("Task1 accomplished!");
    }

    @Async("twoAsync")
    @Override
    public void openThreadTwo() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==================================");
        log.info("Execute method asynchronously" + Thread.currentThread().getName());
    }
}
