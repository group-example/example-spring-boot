package example.spring.boot.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.*;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuluming on 2017/2/14.
 */
public class MailTest extends  BaseTest{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;


    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("461290158@qq.com");
        message.setTo("liuluming1989@qq.com");
        message.setSubject("测试邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);


    }

    @Test
    public void sendAttachmentMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("dyc87112@qq.com");
        helper.setTo("461290158@qq.com");
        helper.setSubject("附件邮件");
        Map<String, Object> model = new HashMap();
//        helper.setText("<html><body><img src=\"cid:xiaoming\" ></body></html>", true);
        FileSystemResource file = new FileSystemResource(new File("/Users/liuluming/tiddlers.html"));
        helper.addInline("xiaoming", file);
//        model.put("username", "didi");
//        String text = VelocityEngineUtils.mergeTemplateIntoString(
//                velocityEngine, "template.vm", "UTF-8", model);
//        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setFrom("dyc87112@qq.com");
//        helper.setTo("461290158@qq.com");
//        helper.setSubject("附件邮件");
//
//        Map<String, Object> model = new HashMap();
//        model.put("username", "didi");
//
//        Context context=new Context();
//        context.setVariables(model);
//        TemplateEngine templateEngine=new SpringTemplateEngine();
//        TemplateResolver templateResolver=new ClassLoaderTemplateResolver();
//        templateEngine.setTemplateResolver(templateResolver);
//        String text = templateEngine.process("templates/index.html",context);
//
//        helper.setText(text, true);
//        mailSender.send(mimeMessage);
    }

}
