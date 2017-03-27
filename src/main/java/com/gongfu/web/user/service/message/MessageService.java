package com.gongfu.web.user.service.message;

import com.gongfu.base.BaseService;
import com.gongfu.model.user.Message;

import java.util.concurrent.Future;

/**
 * Created by a on 2017/3/25.
 */
public interface MessageService extends BaseService<Message> {
    Future<String> openThreadOne();

    void openThreadTwo();
}
