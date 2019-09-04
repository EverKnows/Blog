package FHQ.service;

import FHQ.po.Article;
import FHQ.po.Comment;
import FHQ.vo.ArticleWithComments;

import java.util.List;

public interface ArticleService {
    Integer insertArticle(Article article) throws Exception;

    List<ArticleWithComments> selectArticleWithCommentsByUserId(Integer id) throws Exception;

    List<Article> selectArticleByUserId(Integer userId) throws Exception;

    List<Comment> selectCommentByArticleId(Integer articleId) throws Exception;

    Article getArticleByUserIdAndArticleName(Integer uid, String articleName) throws Exception;

    ArticleWithComments selectArticleWithCommentsByUserIdAndArticleName(Integer uid, String articleName) throws Exception;

    List<Comment> findCommentByArticleId(Integer aid) throws Exception;
}
