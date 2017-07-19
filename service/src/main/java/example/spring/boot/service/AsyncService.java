package example.spring.boot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by liuluming on 2017/7/3.
 */

@Service
public class AsyncService {

    public static Random random =new Random();


    //    注： @Async所修饰的函数不要定义为static类型，否则异步调用不会生效
    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
    public void executeAsyncTask(Integer i){
        System.out.println("executeAsyncTask:"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("executeAsyncTaskPlus:"+i);
    }
}
