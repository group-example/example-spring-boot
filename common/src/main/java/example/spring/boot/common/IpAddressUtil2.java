package example.spring.boot.common;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketException;

/**
 * 获取IP地址工具类 ，可用于logback打印日志使用
 */
@Slf4j
public class IpAddressUtil2 extends PropertyDefinerBase {
    @Override
    public String getPropertyValue() {
        String ip = "no-address";
        try {
            ip = IpAddressUtil.getFristLocalHostIp();
        } catch (SocketException e) {
            log.error("获取IP地址失败");
        }
        return ip;
    }
}
