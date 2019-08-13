package FHQ.po;

import java.util.Date;

public class Article {
    private Integer id;
    private Integer uid;
    private String title;
    private String context;
    private String keyword;
    private String viewcount;
    private String star;
    private Date createtime;
    private Date submittime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getViewcount() {
        return viewcount;
    }

    public void setViewcount(String viewcount) {
        this.viewcount = viewcount;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", keyword='" + keyword + '\'' +
                ", viewcount='" + viewcount + '\'' +
                ", star='" + star + '\'' +
                ", createtime=" + createtime +
                ", submittime=" + submittime +
                '}';
    }
}
