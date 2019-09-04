package FHQ.vo;

import FHQ.po.Article;
import FHQ.po.Comment;

import java.util.List;

public class ArticleWithComments {

    private Article article;

    private Integer commentsCount;

    private List<Comment> commentList;

    @Override
    public String toString() {
        return "ArticleWithComments{" +
                "article=" + article +
                ", commentsCount=" + commentsCount +
                ", commentList=" + commentList +
                '}';
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
