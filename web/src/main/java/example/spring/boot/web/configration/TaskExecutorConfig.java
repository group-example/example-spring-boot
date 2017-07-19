package example.spring.boot.web.configration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置
 * Created by liuluming on 2017/7/10.
 */
@Configuration
// 设置需要被自动注入该线程池的组件包路径
@ComponentScan("example.spring.boot")
public class TaskExecutorConfig implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(TaskExecutorConfig.class);

    //获取配置，并设置缺省值
    @Value("${spring.task.executor.core-pool-size:5}")
    private int corePoolSize;

    @Value("${spring.task.executor.max-pool-size:5}")
    private int maxPoolSize;

    @Value("${spring.task.executor.query-capacity:10}")
    private int queryCapacity;

    @Override
    public Executor getAsyncExecutor() {
        logger.info("创建线程池，获得配置corePoolSize="+corePoolSize);
        logger.info("创建线程池，获得配置maxPoolSize="+maxPoolSize);
        logger.info("创建线程池，获得配置queryCapacity="+queryCapacity);
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);//线程池维护线程的最少数量
        taskExecutor.setMaxPoolSize(maxPoolSize);//线程池维护线程的最大数量
        taskExecutor.setQueueCapacity(queryCapacity);//线程池所使用的缓冲队列
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueryCapacity() {
        return queryCapacity;
    }

    public void setQueryCapacity(int queryCapacity) {
        this.queryCapacity = queryCapacity;
    }
}
