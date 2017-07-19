package example.spring.boot.common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by liuluming on 2017/7/4.
 */
@Configuration
@PropertySource("classpath:system.properties")
@ConfigurationProperties()
public class SystemConfig {

    private String weixinAppId;

    private String weixinAppSecrect;

    private String weixinEncodingAesKey;

    private String weixinInputCharset;

    private String weixinToken;



    public String getWeixinAppId() {
        return weixinAppId;
    }

    public void setWeixinAppId(String weixinAppId) {
        this.weixinAppId = weixinAppId;
    }

    public String getWeixinAppSecrect() {
        return weixinAppSecrect;
    }

    public void setWeixinAppSecrect(String weixinAppSecrect) {
        this.weixinAppSecrect = weixinAppSecrect;
    }

    public String getWeixinEncodingAesKey() {
        return weixinEncodingAesKey;
    }

    public void setWeixinEncodingAesKey(String weixinEncodingAesKey) {
        this.weixinEncodingAesKey = weixinEncodingAesKey;
    }

    public String getWeixinInputCharset() {
        return weixinInputCharset;
    }

    public void setWeixinInputCharset(String weixinInputCharset) {
        this.weixinInputCharset = weixinInputCharset;
    }

    public String getWeixinToken() {
        return weixinToken;
    }

    public void setWeixinToken(String weixinToken) {
        this.weixinToken = weixinToken;
    }
}
