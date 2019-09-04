package FHQ;


import FHQ.po.User;
import FHQ.service.UserService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {
    @Autowired
    public RedisTemplate template;

    @Autowired
    public UserService userService;
    @Test
    public void test() throws Exception {
        User user = new User();
        user.setUsername("Ranly");
//        template.opsForHash().put("names", "username", user);
        userService.findUserByName("Ranly");
    }
}
