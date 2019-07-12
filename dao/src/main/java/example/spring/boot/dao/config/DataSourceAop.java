package example.spring.boot.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 配置自动切换数据源的依据。
 */
@Aspect
@Slf4j
@Component
public class DataSourceAop {

    @Before("execution(* example.spring.boot.dao.mapper.*.select*(..)) || execution(* example.spring.boot.dao.mapper.*.count*(..)) || execution(* example.spring.boot.dao.mapper.*.query*(..))" +
            "||@annotation(example.spring.boot.dao.anotation.ReadDataSource)"
    )
    public void setReadDataSourceType() {
        DataSourceContextHolder.setRead();
    }

    @Before("execution(* example.spring.boot.dao.mapper.*.insert*(..)) || execution(* example.spring.boot.dao.mapper.*.update*(..)) || execution(* example.spring.boot.dao.mapper.*.delete*(..))" +
            "||@annotation(example.spring.boot.dao.anotation.WriteDataSource)")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }
}
