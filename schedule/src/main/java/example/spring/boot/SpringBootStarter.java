package example.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by liuluming on 2017/7/3.
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableCaching
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
