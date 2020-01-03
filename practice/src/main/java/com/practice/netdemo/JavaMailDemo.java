package com.practice.netdemo;

/**
 * 使用JavaMail API发送邮件本质上是一个MUA软件通过SMTP协议发送邮件至MTA服务器；
 * 打开调试模式可以看到详细的SMTP交互信息；
 * 某些邮件服务商需要开启SMTP，并需要独立的SMTP登录密码。
 * 
 * 使用Java接收Email时，可以用POP3协议或IMAP协议。
 * 使用POP3协议时，需要用Maven引入JavaMail依赖，并确定POP3服务器的域名／端口／是否使用SSL等，然后，调用相关API接收Email。
 * 设置debug模式可以查看通信详细内容，便于排查错误。
 */
public class JavaMailDemo{

}