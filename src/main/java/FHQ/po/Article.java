package FHQ.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Article {

    private Integer id;

    @NotNull(message = "作者不能为空")
    private Integer uid;

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "内容不能为空")
    private String context;

    @NotEmpty(message = "MD不能为空")
    private String markdown;

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    private String keyword;

    private String viewcount;

    private Integer star;

    private String tag;

    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @NotNull(message = "创作时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone = "GMT+8")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  //  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone = "GMT+8")
    private Date submittime;

    @NotNull(message = "文章状态不能为空")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
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
