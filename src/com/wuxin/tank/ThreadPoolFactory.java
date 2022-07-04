package com.wuxin.tank;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wuxin001
 * @date: 2022/6/28 14:25
 * @Description: 管理线程类
 */
public final class ThreadPoolFactory {

    private volatile static ThreadPoolExecutor executor;

    private ThreadPoolFactory(){
    }

    /**
     * 创建ExecutorService，单例模式（DCL）
     * @return
     */
    public static ExecutorService getExecutorService(){
        if (executor == null){
            synchronized (ThreadPoolFactory.class){
                if (executor == null){
                    executor = new ThreadPoolExecutor(1, 5, 30, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(5),
                            new CustomThreadFactory("自定义线程"),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }

    /**
     * 自定义线程工厂
     */
    private static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup threadGroup;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public final String namePrefix;

        CustomThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            threadGroup = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            if (null == name || "".equals(name.trim())) {
                name = "pool";
            }
            namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(threadGroup, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}

