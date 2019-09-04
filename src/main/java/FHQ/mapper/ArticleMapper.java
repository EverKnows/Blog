package FHQ.mapper;

import FHQ.po.Article;
import FHQ.po.Comment;
import FHQ.po.User;
import FHQ.vo.ArticleWithComments;
import FHQ.vo.CommentWithWriter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
//    @Insert("insert into t_artile(uid, title, context, createtime) values(#{uid}, " +
//            "#{title}, #{context}, #{createtime})")
//    public Integer addArticle(Article article);

    /**
     *  用于插入文章，即上传文章
     * @param article  需要插入的文章
     * @return 返回改变列的行数
     */
    @Insert("insert into t_article(uid, title, context, tag, pic, createtime, submittime, status, star, markdown) values(#{uid}, " +
            "#{title}, #{context}, #{tag}, #{pic}, #{createtime}, #{submittime}, #{status}, #{star}, #{markdown})")
    public Integer addArticle(Article article);

    /**
     *  用于指定用户的所有文章以及文章的评论
     * @param userId 需要查找文章的用户的id
     * @return
     */
    public List<ArticleWithComments> selectArticleWithCommentsByUserId(@Param(value = "uid") Integer userId);

    /**
     * 用于查找指定用户的指定文章以及该文章的评论
     * @param userId  创作者的id
     * @param articleName 文章的名字
     * @return
     */
    public ArticleWithComments selectArticleWithCommentsByUserIdAndArticleName(@Param(value = "uid") Integer userId, @Param(value = "articleName")String articleName);

    /**
     *  用于查找指定文章的所有评论
     * @param aid 需要查找评论的文章的id
     * @return
     */
    public List<Comment> selectCommentsByArticleId(@Param(value = "aid") Integer aid);


    /**
     *  用于查找指定用户的所有文章
     * @param uid  需要进行查找的用户的id
     * @return
     */
    public List<Article> selectArticleByUserId(@Param(value = "uid") Integer uid);

    /**
     * 查找指定的用户的指定文章
     * @param uid 指定用户的id
     * @param articleName  指定文章的名字
     * @return
     */
    public Article findArticleByUserIdAndArticleName(@Param(value = "uid")Integer uid, @Param(value = "articleName")String articleName);

    /**
     *   查找指定文章的所有评论
     * @param aid
     * @return
     */
    public List<Comment> findCommentsByArticleId(@Param(value = "aid") Integer aid);


}
