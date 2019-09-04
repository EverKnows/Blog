package FHQ;

import FHQ.Task.MailTask;
import FHQ.Utils.MailUtils;
import org.junit.Test;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTest {

    /*@Test
    public void test() throws MessagingException {
        Properties properties = new Properties();
        //发送邮件的服务器地址
        properties.put("mail.smtp.host", "smtp.qq.com");//  stmp.qq.com   smtp.sina.com
        //设置发送邮件使用的传输协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //并且要设置使用验证：
        properties.setProperty("mail.smtp.auth", "true");
        //使用 STARTTLS安全连接:
        properties.setProperty("mail.smtp.starttls.enable","true");
        // 开启debug模式，能够在控制台看到发送邮件的过程
       // properties.setProperty("mail.debug", "true");

        //2.创建Session对象
        Session session = Session.getInstance(properties);

        //3.创建出MimeMessage，邮件的消息对象
        MimeMessage message = new MimeMessage(session);

        //4.设置发件人
        Address fromAddr = new InternetAddress("xu1193932296@163.com");
        message.setFrom(fromAddr);

        //5.设置收件人
        Address toAddr = new InternetAddress("767571265@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, toAddr);


        //6.设置邮件的主题
        message.setSubject("JavaEmail");

        //7.设置邮件的正文
        //message.setText("邮件测试，你收到了吗");
        message.setContent("<b>谢谢</b>", "text/html;charset=utf-8");

        //8.得到传输对象
        Transport transport = session.getTransport();
        //连接SMTP服务器
        //第一个参数是你用来发送邮件的邮箱的SMTP服务器地址，第二个是你的邮箱地址，第三个是授权码
        transport.connect("smtp.qq.com", "xu1193932296@qq.com", "mqqwhiohycyjieea");
        //设置需要发送的信息以及收件人地址，第二个参数是个Address数组：Address[]
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
        System.out.println("关闭");

    }

    @Test
    public void test2() throws Exception {
        String content = "你好！亲爱的<b>Tommms</b>我发布了新文章<b>是是是</b>快来给我点赞吧！";
        try {
            MailTask task = new MailTask("1193932296@qq.com", "1106793581@qq.com", content, "ss");
            task.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
