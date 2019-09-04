package FHQ.service.Impl;

import FHQ.mapper.ArticleMapper;
import FHQ.po.Article;
import FHQ.po.Comment;
import FHQ.service.ArticleService;
import FHQ.vo.ArticleWithComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer insertArticle(Article article) throws Exception{
        Integer result = articleMapper.addArticle(article);
        return result;
    }

    @Override
    public List<ArticleWithComments> selectArticleWithCommentsByUserId(Integer id) throws Exception {
        List<ArticleWithComments> articles = articleMapper.selectArticleWithCommentsByUserId(id);
        for (ArticleWithComments article : articles) {
            article.setCommentsCount(article.getCommentList().size());
        }
        return articles;
    }

    @Override
    public List<Article> selectArticleByUserId(Integer userId) throws Exception {
        List<Article> articles = articleMapper.selectArticleByUserId(userId);
        return articles;
    }

    @Override
    public List<Comment> selectCommentByArticleId(Integer articleId) throws Exception {
        List<Comment> comments = articleMapper.selectCommentsByArticleId(articleId);
        return comments;
    }

    @Override
    public Article getArticleByUserIdAndArticleName(Integer uid, String articleName) throws Exception {
        Article article = articleMapper.findArticleByUserIdAndArticleName(uid, articleName);
        return article;
    }

    @Override
    public ArticleWithComments selectArticleWithCommentsByUserIdAndArticleName(Integer uid, String articleName) throws Exception {
        ArticleWithComments article = articleMapper.selectArticleWithCommentsByUserIdAndArticleName(uid, articleName);
        return article;
    }

    @Override
    public List<Comment> findCommentByArticleId(Integer aid) throws Exception {
        List<Comment> comments = articleMapper.findCommentsByArticleId(aid);
        return comments;
    }
}
