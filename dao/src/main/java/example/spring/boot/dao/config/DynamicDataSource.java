package example.spring.boot.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Random;

/**
 * 主从切换；从库负载均衡；
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static int readDataSourceAmount = 1;

    private static volatile DynamicDataSource instance;

    private DynamicDataSource() {
    }


    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        String name = null;
        if (typeKey.equals(DataSourceTypeEnum.WRITE.getCode())) {
            name = DataSourceTypeEnum.WRITE.getCode();
        }
        // 读库,并且简单负载均衡。
        else if (typeKey.equals(DataSourceTypeEnum.READ.getCode())) {
            name = DataSourceTypeEnum.READ.getCode() + (new Random().nextInt(readDataSourceAmount) + 1);

        }
        log.info("当前使用的数据源：" + name);
        return name;
    }

    public static DynamicDataSource getInstance() {
        if (instance == null) {
            synchronized (DynamicDataSource.class) {
                if (instance == null) {
                    instance = new DynamicDataSource();
                }
            }
        }
        return instance;
    }


}
