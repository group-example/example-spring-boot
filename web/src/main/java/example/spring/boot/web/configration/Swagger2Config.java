package example.spring.boot.web.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swwager2的配置文件
 * 请求路径：应用路径/swagger-ui.html
 *
 * @author liuluming
 */
@Configuration //告诉spring加载该类该配置类，相当于xml里面的<beans>标签
@EnableSwagger2 //启用swagger2
public class Swagger2Config {

    @Bean //相当于xml里面的<bean>标签
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("example.spring.boot.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("example-spring-boot-web 项目 API测试文档")
                .description("使用spring-boot构建")
//                .termsOfServiceUrl("http://blog.didispace.com/")
//                .contact("刘路明")
                .version("1.0")
                .build();
    }
}
