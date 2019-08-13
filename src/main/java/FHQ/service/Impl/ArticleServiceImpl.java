package FHQ.service.Impl;

import FHQ.mapper.ArticleMapper;
import FHQ.po.Article;
import FHQ.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer insertArticle(Article article) throws Exception{
        Integer result = articleMapper.queryUserInfo(article);
        return result;
    }
}
