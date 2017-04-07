package com.gongfu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 向志佳 on 2017/3/25.
 * 定义了两个不同的Executor，
 * 第二个重新设置了pool已经达到max size时候的处理方法；同时指定了线程名字的前缀
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    private int corePoolSize = 10;
    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    private int maxPoolSize = 200;
    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    private int queueCapacity = 10;

    /**
     * 第一个线程池
     *
     * @return
     */
    @Bean
    public Executor oneAsync() {
        ThreadPoolTaskExecutor twoExecutor=newExecutor("oneExecutor-");
        twoExecutor.initialize();
        return twoExecutor;
    }

    /**
     * 第二个线程池
     *
     * @return
     */
    @Bean
    public Executor twoAsync() {
        ThreadPoolTaskExecutor twoExecutor=newExecutor("twoExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        twoExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        twoExecutor.initialize();
        return twoExecutor;
    }

    private ThreadPoolTaskExecutor newExecutor(String executorName){
        ThreadPoolTaskExecutor twoExecutor = new ThreadPoolTaskExecutor();
        twoExecutor.setCorePoolSize(corePoolSize);
        twoExecutor.setMaxPoolSize(maxPoolSize);
        twoExecutor.setQueueCapacity(queueCapacity);
        twoExecutor.setThreadNamePrefix(executorName);
        return twoExecutor;
    }
}
