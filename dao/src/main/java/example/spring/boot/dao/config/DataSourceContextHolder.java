package example.spring.boot.dao.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void setRead() {
        log.debug("current DataSource type is: " + DataSourceTypeEnum.READ.getCode());
        local.set(DataSourceTypeEnum.READ.getCode());
    }

    /**
     * 写只有一个库
     */
    public static void setWrite() {
        log.debug("current DataSource type is: " + DataSourceTypeEnum.WRITE.getCode());
        local.set(DataSourceTypeEnum.WRITE.getCode());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
