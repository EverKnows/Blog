package FHQ.controller;

import FHQ.po.Article;
import FHQ.po.Comment;
import FHQ.po.Page;
import FHQ.po.User;
import FHQ.service.ArticleService;
import FHQ.service.CommentService;
import FHQ.service.UserService;
import FHQ.vo.ArticleWithComments;
import FHQ.vo.CommentWithWriter;
import ch.qos.logback.classic.Logger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BlogController {
    private Logger logger = (Logger) LoggerFactory.getLogger(getClass());



    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/index/{writer}")
    public String index(@PathVariable(value = "writer") String writername, Page page, Model model) throws Exception {
        if (page == null) {
            page = new Page();
            page.setCur(1);
            page.setSize(5);
        } else if(page.getCur() == null || page.getSize() == null) {
            page.setCur(1);
            page.setSize(5);
        }
        User writer = userService.findUserByName(writername);
        PageHelper.startPage(page.getCur(), page.getSize());
        List<ArticleWithComments> articleWithComments = articleService.selectArticleWithCommentsByUserId(writer.getId());
        PageInfo<ArticleWithComments> pageInfo = new PageInfo<>(articleWithComments);
        model.addAttribute("writer", writer);
        model.addAttribute("articles", articleWithComments);
        model.addAttribute("curPage", pageInfo.getPageNum());
        model.addAttribute("totalPage", pageInfo.getPages());
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "logins";
    }

    @PostMapping("/follow/{id}")
    @ResponseBody
    public Map follow(@PathVariable(value = "id") Integer followerId, HttpSession session) throws Exception {
        System.out.println("================Done===================");
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User)session.getAttribute("user");
        Integer userId = user.getId();
        if (userId == followerId) {
            map.put("error", "你不能关注自己！");
            return map;
        }
        Boolean result = userService.addFollowRelation(userId, followerId);
        if (result) {
            map.put("success", "成功关注！");
            return map;
        } else {
            map.put("error", "你已经关注了他！");
            return map;
        }


    }
    @GetMapping("/index/{writer}/{article}")
    public String singleBlog(@PathVariable(value = "writer")String writername, @PathVariable(value = "article") String articlename, Model model) throws Exception {
        User writer = userService.findUserByName(writername);
        Integer id = writer.getId();
//        ArticleWithComments article = articleService.selectArticleWithCommentsByUserIdAndArticleName(id, articlename);
        Article article = articleService.getArticleByUserIdAndArticleName(id, articlename);
        Integer articleId = article.getId();
        List<CommentWithWriter> commentWithWriters = userService.findCommentWithWrtierByArticleId(articleId);
        Collections.sort(commentWithWriters, new Comparator<CommentWithWriter>() {
            @Override
            public int compare(CommentWithWriter o1, CommentWithWriter o2) {
                if (o1.getComment().getCreatetime().getTime() == o2.getComment().getCreatetime().getTime()) {
                    return 0;
                } else {
                    return o1.getComment().getCreatetime().getTime() < o2.getComment().getCreatetime().getTime() ? 1 : -1;
                }
            }
        });
        System.out.println("=============="+ article.toString());
        model.addAttribute("writer", writer);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentWithWriters);
        return "index-single";
    }

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{writer}/{article}")
    @ResponseBody
    public Map addComment(@RequestBody Comment comment, HttpSession session, @PathVariable(value = "writer")String writerName
                ,@PathVariable(value = "article")String articleName) throws Exception {
        System.out.println("============================Comment is Done=======================");
        Integer uid = ((User) session.getAttribute("user")).getId();
        Integer id = userService.findUserByName(writerName).getId();
        Article article = articleService.getArticleByUserIdAndArticleName(id, articleName);
        Integer aid = article.getId();
        comment.setAid(aid);
        comment.setUid(uid);
        System.out.println("=========Here============");
        Integer integer = commentService.insertComment(comment);
        Map<String, Object> map = new HashMap<String, Object>();
        if (integer != null){
            map.put("success", "评论成功");
        } else {
            map.put("error", "评论失败");
        }
        return map;

    }
}
