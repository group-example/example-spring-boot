package example.spring.boot.web.configration;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取IP工具类 ，用于logback打印日志使用
 *
 * @author liu
 */
public class LogBackIpConfig extends ClassicConverter {

    private static String fristLocalHostIp = null;

    @Override
    public String convert(ILoggingEvent event) {
        String ip = null;
        try {
            ip = getFristLocalHostIp();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    private static String getFristLocalHostIp() throws SocketException {
        if (fristLocalHostIp != null) {
            return fristLocalHostIp;
        }
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        String ipStr = "127" + ".0.0.1";
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (ip instanceof Inet4Address && !ipStr.equals(ip.getHostAddress())) {
                    fristLocalHostIp = ip.getHostAddress();
                    ipStr = ip.getHostAddress();
                    break;
                }
            }
            if (fristLocalHostIp != null) {
                break;
            }
        }
        return ipStr;
    }
}
