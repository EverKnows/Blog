package FHQ.controller;


import FHQ.Task.MailTask;
import FHQ.Validation.ModifyValidate;
import FHQ.po.Article;
import FHQ.po.Page;
import FHQ.po.User;
import FHQ.service.ArticleService;
import FHQ.service.UserService;
import FHQ.vo.ArticleWithComments;
import ch.qos.logback.classic.Logger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import FHQ.Task.*;

@Controller
public class HomeController {

    Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/home/modify")
    public String homeModify() {
        return "home-modify";
    }

    @PutMapping("/home/modify")
    @ResponseBody
    public Map modifyInfo(@RequestBody @Validated(ModifyValidate.class) User user, BindingResult bindingResult, HttpSession session) throws Exception {
        logger.debug("start modify userinfo");
        Map<String, Object> map = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            map.put("error", errors.get(0).getDefaultMessage());
            return map;
        }
        Integer integer = userService.updateUserInfo(user, session);
        if (integer != null) {
            map.put("success", "修改成功");
        } else {
            map.put("error", "修改失败");
        }
        System.out.println((User) session.getAttribute("user"));
        return map;
    }


    @GetMapping("/home/search")
    public String searchPage() {
        return "home-search";
    }

    @GetMapping("/home/searchs")
    public String searchUser(@RequestParam(required = false) String username, Page page, Model model) throws Exception {
        if (username == null || username.length() == 0) {
            return "home-search";
        }
        System.out.println("Done");
        PageHelper.startPage((page == null)?1:page.getCur(), (page == null)?5:page.getSize());
        List<User> list = userService.searchUserLikeName(username);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        model.addAttribute("users", list);
        model.addAttribute("username", username);
        model.addAttribute("curPage", pageInfo.getPageNum());
        model.addAttribute("totalPage", pageInfo.getPages());
        System.out.println(pageInfo.getPages());
        return "home-search";
    }

    @GetMapping("/home/write")
    public String writePage() {
        return "home-write";
    }

    @PutMapping("home/write")
    @ResponseBody
    public Map saveArticle(@RequestBody @Validated Article article, BindingResult bindingResult, HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            map.put("error", errors.get(0).getDefaultMessage());
            return map;
        }
        Integer integer = articleService.insertArticle(article);
        if (integer == null) {
            if(article.getStatus() == 0) {
                map.put("error", "保存失败");
            } else {
                map.put("error", "发布失败");
            }

        } else {
            if(article.getStatus() == 0) {
                map.put("success", "保存成功");
            } else {
                map.put("success", "发布成功");
            }
        }
        User from = (User) session.getAttribute("user");

        List<User> followers = userService.getMyFollowers(from.getId());
        System.out.println("=======开始发送邮件=========" + followers.size());
        for (int i = 0; i < followers.size(); i++) {
            User to = followers.get(i);
            String msg = "你好!亲爱的<b>" + to.getUsername() + "</b>" + "我发布了新文章" + "<b>"+article.getTitle()+"</b>" + "快来给我点赞吧!";
            MailTask task = new MailTask(from.getEmail(), to.getEmail(), msg, "更新提醒");
            threadPoolTaskExecutor.execute(task);
        }
        Thread.sleep(100);
        System.out.println("=======邮件发送结束=========");
        return map;
    }

    @GetMapping("/home/article")
    public String getArticles(Page page, Model model, HttpSession session) throws Exception {
        if (page == null) {
            page = new Page();
            page.setCur(1);
            page.setSize(5);
        } else if(page.getCur() == null || page.getSize() == null) {
            page.setCur(1);
            page.setSize(5);
        }
        System.out.println("Page : " + page);
        System.out.println("===================DONE1======================");
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        System.out.println("USERID : " + userId);
        PageHelper.startPage(page.getCur(), page.getSize());
        List<ArticleWithComments> articleWithCommentsList = articleService.selectArticleWithCommentsByUserId(userId);
        System.out.println("===================DONE2======================");
        PageInfo<ArticleWithComments> pageInfo = new PageInfo<>(articleWithCommentsList);
        model.addAttribute("curPage", pageInfo.getPageNum());
        model.addAttribute("totalPage", pageInfo.getPages());
        model.addAttribute("articles", articleWithCommentsList);
        return "home-article";
    }
}
