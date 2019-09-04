package FHQ.service.Impl;

import FHQ.mapper.UserMapper;
import FHQ.po.Page;
import FHQ.po.User;
import FHQ.service.UserService;
import FHQ.vo.CommentWithWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) throws Exception {
        Integer sum = null;
        if (queryUserNumByName(user.getUsername()) == 0 || queryUserNumByName(user.getUsername()) == null ) {
            sum = userMapper.addtUser(user);
        } else {
            return null;
        }

        return sum;
    }

    @Cacheable(value="Users")
    @Override
    public Integer queryUserNumByName(String username) throws Exception {
        return userMapper.queryUserNumByName(username);
    }

    @Override
    public Integer updateUserInfo(User user, HttpSession session) throws Exception {
        User cur = (User) session.getAttribute("user");
        user.setId(cur.getId());
        System.out.println(user);
        Integer integer = userMapper.updateUser(user);

        if (integer != null) {
            BeanUtils.copyProperties(user, (User)session.getAttribute("user"));
        }
        return integer;
    }

    @Override
    public User selectUserById(Integer id) throws Exception {
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public Boolean addFollowRelation(Integer uid, Integer fid) throws Exception {
        Integer result = userMapper.findFollowRelation(uid, fid);
        if(result == 0 || result == null) {
            userMapper.followUserById(uid, fid);
            return true;
        }
        return false;
    }

    @Override
    public List<User> searchUserLikeName(String username) throws Exception {

        List<User> users = userMapper.searchUserLikeName(username);

        return users;
    }

    @Cacheable(value="Users")
    @Override
    public User findUserByName(String username) throws Exception {
        User user = userMapper.queryUserByName(username);
        return user;
    }

    @Override
    public List<CommentWithWriter> findCommentWithWrtierByArticleId(Integer aid) throws Exception {
        List<CommentWithWriter> commentWithWriters = userMapper.findCommentWithWriterByArticleId(aid);
        return commentWithWriters;
    }

    @Override
    public List<User> getMyFollowers(Integer uid) throws Exception {
        List<User> followers = userMapper.getAllMyFollowers(uid);
        return followers;
    }
}
