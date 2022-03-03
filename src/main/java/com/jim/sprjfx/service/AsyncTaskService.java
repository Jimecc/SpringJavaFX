package com.jim.sprjfx.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {
    @Async("ThreadAsync")
    public void executeAsyncTask(int i) {
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }
}