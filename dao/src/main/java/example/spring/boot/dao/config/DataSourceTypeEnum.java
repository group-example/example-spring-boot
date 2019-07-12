package example.spring.boot.dao.config;

/**
 * 数据库 读写分离  类型配置。
 */
public enum DataSourceTypeEnum {
    WRITE("write", "主库"),
    READ("read", "从库");

    DataSourceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
