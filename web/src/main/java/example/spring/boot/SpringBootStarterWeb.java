package example.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by liuluming on 2017/7/3.
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
public class SpringBootStarterWeb {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterWeb.class, args);
        //test
    }
}
