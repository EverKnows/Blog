package FHQ;

import FHQ.mapper.ArticleMapper;
import FHQ.mapper.UserMapper;
import FHQ.po.Comment;
import FHQ.po.User;
import FHQ.service.Impl.UserServiceImpl;
import FHQ.service.UserService;
import FHQ.vo.CommentWithWriter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {
//    @Test
//    public void test() throws Exception {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserService service = applicationContext.getBean(UserService.class);
//        User user = new User();
//        user.setId(4);
//        user.setEmail("222222222@qq.com");
//        user.setRealname("徐建淼");
//        user.setTag("热情积极团结友爱");
//        System.out.println(user);
//        Integer integer = service.updateUserInfo(user, null);
//        System.out.println(integer);
//    }
//    @Test
//    public void test1() {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        ArticleMapper bean = applicationContext.getBean(ArticleMapper.class);
//        List<Comment> comments = bean.findCommentsByArticleId(47);
//        System.out.println(comments);
//
//    }
//
//    @Test
//    public void test2() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserMapper bean = context.getBean(UserMapper.class);
//        List<CommentWithWriter> commentWithWriters = bean.findCommentWithWriterByArticleId(47);
//        System.out.println(commentWithWriters);
//    }
//
//    @Test
//    public void test3() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserMapper bean = context.getBean(UserMapper.class);
//        List<User> followers = bean.getAllMyFollowers(3);
//        System.out.println(followers);
//    }
}
