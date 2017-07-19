package example.spring.boot.web;

import example.spring.boot.service.AsyncService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

/**
 * Created by liuluming on 2017/2/14.
 */

public class AsyncTest extends BaseTest {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private AsyncService asyncService;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncService.doTaskOne();
        Future<String> task2 = asyncService.doTaskTwo();
        Future<String> task3 = asyncService.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }



}
