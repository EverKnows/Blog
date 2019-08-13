package FHQ.Utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Properties;

public class MailUtils {
    //发送人
    public String sender = "1193932296@qq.com";

    //授权码
    public final static String AUTH_CODE = "mqqwhiohycyjieea";

    //邮箱配置
    private static Properties props = new Properties();

    static {
        String path = MailUtils.class.getClassLoader().getResource("mail.properties").getPath();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送邮件
    public static void sendMail(String from, Object to, String context, String subject, String verifyCode) throws Exception {
        //收件人数组
        InternetAddress[] receiver = null;

        //1.根据props创建session对象，由于和邮箱服务器进行交互
        Session session = Session.getInstance(props);

        //创建mimeMessage对象用于发送邮件
        MimeMessage message = new MimeMessage(session);

        //设置发送人
        message.setFrom(new InternetAddress(from));

        //设置收件人
        if (to.getClass().isArray()) {
            int len = Array.getLength(to);
            receiver = new InternetAddress[len];
            for (int i = 0; i < len; i++) {
                receiver[i] = new InternetAddress(Array.get(to, i).toString());
            }
            message.setRecipients(Message.RecipientType.TO, receiver);
        } else {
            message.setRecipients(Message.RecipientType.TO, to.toString());
        }


        //设置主题
        if (subject == null) {
            message.setSubject("欢迎加入孵化器博客");
        } else {
            message.setSubject(subject);
        }


        //设置邮件内容

        if (context == null) {
            message.setContent("<b>欢迎加入孵化器</b>", "text/html;charset=utf-8");
        } else {
            message.setSubject(context, "text/html;charset=utf-8");
        }

        //发送邮件
        Transport transport = session.getTransport();

        System.out.println("开始发送邮件");

        //设置发送人和
        transport.connect(props.getProperty("mail.smtp.host"), from, MailUtils.AUTH_CODE);

        //设置需要发送的信息以及收件人地址，第二个参数是个Address数组：Address[]
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
        System.out.println("邮件发送完毕");

    }
}
