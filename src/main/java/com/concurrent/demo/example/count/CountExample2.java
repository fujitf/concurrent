package com.concurrent.demo.example.count;

import com.concurrent.demo.annoations.NotThreadSafe;
import com.concurrent.demo.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Target;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：fangmeixiu
 * @Description:
 * @Date :create in 上午7:36 2019/4/1
 */
@Slf4j
@ThreadSafe
public class CountExample2 {
    //请求总数
    public   static int clientTotal=5000;
    //同时并发执行的线程数
    public  static int  threadTotal=200;
    public  static AtomicInteger count=new AtomicInteger(0);
    public static void main(String[] args) throws Exception {
        //定义一个线程池
        ExecutorService executorService= Executors.newCachedThreadPool();
        //定义信号量，给出允许的并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:"+count.get());


    }

    private  static  void  add(){
        count.incrementAndGet();
        //count.getAndIncrement();
    }
}
