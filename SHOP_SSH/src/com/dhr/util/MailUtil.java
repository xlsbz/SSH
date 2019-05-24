package com.dhr.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具
 * @author Mr DU
 *
 */
public class MailUtil {

	public static void sendMail(String address,String password,String to,String code) {
		//1.获取邮件会话对象
		Properties props = new Properties();
			//设置邮件服务器等信息
			props.setProperty("mail.host", "localhost");//本地邮件服务器
			props.setProperty("mail.smtp.auth", "true");
			//设置用户名密码
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(address, password);
				}
			};
		Session session = Session.getInstance(props, auth);
		//2.创建mimemessage对象
		MimeMessage message = new MimeMessage(session);
			//设置发件人，收件人，主体，主题
		    try {
				message.setFrom(new InternetAddress(address));
				message.addRecipients(RecipientType.TO, to);//TO:明送
				message.setSubject("这是一封来自商城的官方邮件！");
				message.setContent("<a href='http://172.19.7.43:8080/SHOP_SSH/user_active.action?code="+code+"'>恭喜您注册成功，点击这条链接激活账号!</a>","text/html;charset=utf-8");
			
			//3.发送邮件
				Transport.send(message);
		    } catch (Exception e) {
				e.printStackTrace();
			}
	}
}
