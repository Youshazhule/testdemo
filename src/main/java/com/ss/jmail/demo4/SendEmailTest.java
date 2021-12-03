package com.ss.jmail.demo4;

import com.alibaba.fastjson.JSONArray;
 
import com.alibaba.fastjson.JSONObject;
 
 
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
import org.apache.commons.lang.StringUtils;

 
import com.sun.mail.util.MailSSLSocketFactory;
 
public class SendEmailTest {

	public static void main(String[] args) throws Exception {
		sendEmail();
	}
	
	public static void sendEmail() throws Exception {
		Properties props = new Properties();
	    props.put("mail.transport.protocol", "smtp");  //smtp服务器
	    props.put("mail.smtp.host", "smtp.163.com");     //主机host
	    props.put("mail.smtp.auth", "true");    //
	    props.put("mail.smtp.port", "25");    //端口
 
	    Session session = Session.getDefaultInstance(props, new Authenticator()
	    {
	      protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("hcx313318@163.com","ZC313318.....");
	      }
	    });
		session.setDebug(true);

		Message message = createAttachMail(session);
		Transport.send(message);
	}

	public static MimeMessage createAttachMail(Session session)
			throws Exception {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("hcx313318@163.com"));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress("jack_cai123@163.com"));
				message.setSubject("只包含文本的简单邮件");
				message.setContent("你好啊123123123！", "text/html;charset=UTF-8");
				return message;
			}
 
	
}