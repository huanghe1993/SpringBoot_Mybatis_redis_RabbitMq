package com.huanghe.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: River
 * @Date:Created in  9:46 2018/10/28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSender mailSender;

    @Test
    public void sendMailTest(){
        //简单的邮件设置
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:00开会");
        message.setTo("hhriver0601@163.com");
        message.setFrom("735597346@qq.com");
        mailSender.send(message);
    }

    /**
     * 测试发送复杂的邮件
     */
    @Test
    public void testMEMIMail() throws MessagingException {
        //复杂邮件设置
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今晚7:00开会</br>",true);
        helper.setTo("hhriver0601@163.com");
        helper.setFrom("735597346@qq.com");
        //上传附件
        helper.addAttachment("1.jpg",new File("G:\\照片\\1.jpg"));

        mailSender.send(mimeMessage);
    }
}
