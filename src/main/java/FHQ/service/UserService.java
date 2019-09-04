package FHQ.service;

import FHQ.po.Article;
import FHQ.po.Page;
import FHQ.po.User;
import FHQ.vo.CommentWithWriter;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    Integer addUser(User user) throws Exception;

    Integer queryUserNumByName(String username) throws Exception;

    Integer updateUserInfo(User user, HttpSession session) throws Exception;

    User selectUserById(Integer id) throws Exception;

    List<User> searchUserLikeName(String username) throws Exception;

    User findUserByName(String username) throws Exception;

    Boolean addFollowRelation(Integer uid, Integer fid) throws Exception;

    List<CommentWithWriter> findCommentWithWrtierByArticleId(Integer aid) throws Exception;

    List<User> getMyFollowers(Integer uid) throws Exception;
}
