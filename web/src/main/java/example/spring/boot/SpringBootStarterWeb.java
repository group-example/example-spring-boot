package example.spring.boot;

import example.spring.boot.web.aspect.PrometheusMetricsInterceptor;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableCaching
@Slf4j
public class SpringBootStarterWeb implements CommandLineRunner, WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterWeb.class, args);
    }

    @Value("${prometheus.port:1234}")
    private int port;

    @Override
    public void run(String... strings) throws Exception {
        //暴露的HTTP默认访问路径为/prometheus。
        log.info("=============启用Prometheus,端口号{}=============", port);
        //Prometheus提供，用于监控JVM状态，开箱即用。
        DefaultExports.initialize();
        try {
            new HTTPServer(port);
        } catch (IOException e) {
            log.error("Prometheus服务启动失败");
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("=============启用Prometheus拦截器=============");
        registry.addInterceptor(new PrometheusMetricsInterceptor()).excludePathPatterns("/webjars**");
    }
}
