package example.spring.boot.web.handler;

import example.spring.boot.common.BusinessException;
import example.spring.boot.common.GeneralResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuluming on 2017/2/13.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public GeneralResponse<String> errorHandler1(HttpServletRequest req, BusinessException e) {
        logger.warn(e.getMessage(),e);
        GeneralResponse<String> r = new GeneralResponse<>();
        r.setSuccessful(false);
        r.setMessage(e.getMessage());
        return r;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GeneralResponse<String> errorHandler2(HttpServletRequest req, Exception e) {
        GeneralResponse<String> r = new GeneralResponse<>();
        r.setSuccessful(false);
        r.setMessage("系统内部出错" );
        r.setDevelopMsg(ExceptionUtils.getStackTrace(e).substring(0,200).replace("\n\t","!!  "));
        logger.error(e.getMessage(),e);
        return r;
    }
}
