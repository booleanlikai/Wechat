package com.likai.gateway.executor;

import com.likai.gateway.model.menu.menuResponse;

import java.util.concurrent.*;

public class menuInfoOption<T> {
    private final static ExecutorService executor = Executors.newCachedThreadPool();

    public T submitTask(Callable<T> task) throws InterruptedException, ExecutionException, TimeoutException {
        Future<T> task1 = executor.submit(task);
        T result = task1.get(10, TimeUnit.SECONDS);
        return result;
    }





}
