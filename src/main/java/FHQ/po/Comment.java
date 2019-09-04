package FHQ.po;

import java.util.Date;

public class Comment {

    private Integer id;

    private Integer aid;

    private Integer uid;

    private String context;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", aid=" + aid +
                ", uid=" + uid +
                ", context='" + context + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
