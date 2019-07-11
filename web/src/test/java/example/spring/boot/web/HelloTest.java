package example.spring.boot.web;

//import example.spring.boot.common.SystemConfig;
import example.spring.boot.dao.model.Hello;
import example.spring.boot.service.HelloService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuluming on 2017/2/14.
 */
public class HelloTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HelloService helloService;
//
//    @Autowired
//    private SystemConfig systemConfig;

    @Test
    public void insetTest() throws Exception {
        Hello hello = new Hello();
        hello.setAge(311);
        hello.setName("51");

        List<Future<Integer>> futureList=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            Future<Integer> future=helloService.insert1(hello);
            futureList.add(future);
        }
        AtomicInteger failedTimes = new AtomicInteger(0);
        while(true){
            failedTimes.set(0);
            boolean allDone=true;
            for(Future future:futureList){
                if(!future.isDone()){
                    allDone=false;
                    break;
                }
                if(future.get().equals(0)){
                    failedTimes.addAndGet(1);
                }
            }
            if(allDone){
                break;
            }
        }
        System.out.println("执行结果：失败次数="+failedTimes);
    }

    class InsertTest implements Runnable {
        @Override
        public void run() {
            Hello hello = new Hello();
            hello.setAge(1);
            hello.setName("51");
            try {
                helloService.insert(hello);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    @Test
//    public void configTest() {
//        String test = systemConfig.getWeixinToken();
//        logger.info("配置测试:token=" + test);
//        String appId = systemConfig.getWeixinAppId();
//        logger.info("配置测试:appId=" + appId);
//    }

    @Test
    public void selectTest() {
        logger.info("第一次查询");
        logger.info("结果集大小：" + helloService.findByName("AAA").size());
        logger.info("第二次查询");
        logger.info("结果集大小：" + helloService.findByName("AAA").size());
    }

}
