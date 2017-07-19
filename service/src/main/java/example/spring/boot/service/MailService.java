package example.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by liuluming on 2017/2/16.
 */
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
}
