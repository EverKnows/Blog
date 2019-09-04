package FHQ.Task;

import FHQ.Utils.MailUtils;
import FHQ.po.User;


public class MailTask extends Thread {

    private String msg = null;

    private String to = null;

    private String from = null;

    private String subject = null;

    public MailTask(String from, String to, String msg, String subject) {
        this.from = from;
        this.msg = msg;
        this.to = to;
        this.subject = subject;
    }

    @Override
    public void run() {
        try {
            MailUtils.sendMail(from, to, msg, subject, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
