package example.spring.boot.web.aspect;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Prometheus监控，拦截全部web请求，用于统计
 */
@Slf4j
public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {


    /**
     * // 统计每个接口请求耗时。
     * 可用于：单次耗时、平均耗时；
     */
    static final Gauge requestCost = Gauge.build()
            .name("http_request_cost").labelNames("path", "method", "status").help("Http Request Cost").register();

    /**
     * // 每个接口请求计数。
     * 可用于：QPS、请求总数；
     */
    static final Counter requestCounter = Counter.build()
            .name("http_request_counter").labelNames("path", "method", "status").help("Http Request Count").register();

    /**
     * // 统计接口请求耗时分布直方图
     */
    static final Histogram requestHistogram = Histogram.build().
            buckets(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000,
                    4000,5000,6000,7000,8000,9000,10000).
            name("http_request_histogram").help("Histogram Of Http Request").register();

    /**
     * 记录当前请求线程的开始时间
     */
    private ThreadLocal<Long> timeMillis = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        timeMillis.set(System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();
        long costTimeMillis = System.currentTimeMillis() - timeMillis.get();
        timeMillis.remove();
        requestCost.labels(requestUri, method, String.valueOf(status)).set(costTimeMillis);
        requestCounter.labels(requestUri, method, String.valueOf(status)).inc();
        requestHistogram.observe(costTimeMillis);
        super.afterCompletion(request, response, handler, ex);
    }
}