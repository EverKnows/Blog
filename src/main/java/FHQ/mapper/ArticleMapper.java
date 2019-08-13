package FHQ.mapper;

import FHQ.po.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    @Insert("insert into t_artile(uid, title, context, keyword, createtime) values(#{uid}, " +
            "#{title}, #{context}, #{keyword}, #{createtime})")
    public Integer queryUserInfo(Article article);
}
