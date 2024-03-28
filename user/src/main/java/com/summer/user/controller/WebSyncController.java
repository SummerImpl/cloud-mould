package com.summer.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.io.Serializable;


@RestController
@RequestMapping(value = "/async")
public class WebSyncController implements Serializable {

    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor threadPoolExecutor;


    @GetMapping(value = "/task")
    public WebAsyncTask<String> asyncTaskCompletion() {
        System.out.println("当前线程" + Thread.currentThread().getName());

        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            System.out.println("异步线程" + Thread.currentThread().getName());
            Thread.sleep(5 * 1000L);
            return "异步";
        });

        asyncTask.onCompletion(() -> {
            System.out.println("执行任务完成");
        });
        System.out.println("处理其他任务");
        return asyncTask;
    }

    @GetMapping(value = "/exception")
    public WebAsyncTask<String> asyncTaskException() {
        System.out.println("当前线程： " + Thread.currentThread().getName());
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            System.out.println("异步线程 ： " + Thread.currentThread().getName());
            Thread.sleep(5 * 10L);
            throw new RuntimeException("执行当前线程异常了");
        });

        asyncTask.onCompletion(() -> {
            System.out.println("异步线程处理完毕");
        });

        asyncTask.onError(() -> {
            System.out.println("任务异常了");
            return "任务异常";
        });

        System.out.println("继续处理其他任务");
        return asyncTask;
    }


    @GetMapping(value = "/timeout")
    public WebAsyncTask<String> asyncTaskTimeout() {
        System.out.println("这是超时 任务的当前线程 ： " + Thread.currentThread().getName());
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            System.out.println("超时 异步任务的线程 ： " + Thread.currentThread().getName());
            Thread.sleep(15 * 1000L);
            return "time Out";
        });

        asyncTask.onCompletion(() -> {
            System.out.println("异步线程处理完毕");
        });

        asyncTask.onTimeout(() -> {
            System.out.println("超时了");
            return "timeOut";
        });

        System.out.println("继续处理其他任务");

        return asyncTask;
    }

    @GetMapping(value = "/pool")
    public WebAsyncTask<String> asyncTaskThreadPool() {
        System.out.println("当前线程 ： " + Thread.currentThread().getName());
        return new WebAsyncTask<>(5 * 1000L, threadPoolExecutor, () -> {
            System.out.println("当前异步线程 ： " + Thread.currentThread().getName());
            return "线程池";
        });
    }
}
