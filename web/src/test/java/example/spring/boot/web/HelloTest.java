package example.spring.boot.web;

import example.spring.boot.common.SystemConfig;
import example.spring.boot.dao.model.Hello;
import example.spring.boot.service.HelloService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liuluming on 2017/2/14.
 */
public class HelloTest extends  BaseTest{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HelloService helloService;

    @Autowired
    private SystemConfig systemConfig;

    @Test
    public void insetTest() throws Exception {
//        helloService.insert("CCC");
        List<Hello> u = helloService.findByName("AAA");
        System.out.println("u.size="+u.size());
    }

//    @Test
//    public void updateTest(){
//        helloService.update("EEE",new Long(1));
//        logger.info("更新测试");
//    }
    @Test
    public void configTest(){
        String test=systemConfig.getWeixinToken();
        logger.info("配置测试:token="+test);
        String appId=systemConfig.getWeixinAppId();
        logger.info("配置测试:appId="+appId);
    }

    @Test
    public void selectTest(){
        logger.info("第一次查询");
        logger.info("结果集大小："+ helloService.findByName("AAA").size());
        logger.info("第二次查询");
        logger.info("结果集大小："+ helloService.findByName("AAA").size());
    }

}
