package FHQ.mapper;

import FHQ.po.User;
import FHQ.vo.CommentWithWriter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UserMapper {


    /**
     * 新增用户
     * @param user  给定的用户信息
     * @return
     */
    @Insert("insert into t_user(username, password, email) values(#{username}, #{password}, #{email})")
    public Integer addtUser(User user);


    /**
     * 查看指定用户名的用户是否存在
     * @param username
     * @return
     */
    @Select("select count(*) from t_user where username=#{username}")
    public Integer queryUserNumByName(String username);


    @Select("select * from t_user where username=#{username}")
    public User queryUserByName(String username);


    /**
     * 根据用户名来筛选id，这里是一对一的关系
     * @param username  给定的用户名
     * @return 符合条件的用户
     */
    @Select("select * from t_user where username=#{username}")
    @Results(id = "accResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "pic",column = "pic"),
            @Result(property = "email",column = "email"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "avatar",column = "avatar")
    })
    public User selectUserByName(String username);


    /**
     * 根据给定信息来更新对应的有用户的个人信息
     * @param user  给定的用户信息 包含id
     * @return
     */
    public Integer updateUser(@Param(value = "user")User user);

    /**
     * 根据给定的id查找用户， 一对一
     * @param id  给定的用户id
     * @return
     */
    @Select("select * from t_user where id = #{uid}")
    @ResultMap("accResultMap")
    public User selectUserById(Integer id);


    /**
     *  查找用户名与给定用户名相似的用户  ‘%username%’
     * @param username  给定的用户名，即查找条件
     * @return 符合条件的用户列表
     */
    public List<User> searchUserLikeName(@Param(value = "username") String username);


    /**
     *  插入follow关系
     * @param uid  被follow的作者的id
     * @param fid  follower的id
     * @return
     */
    public Integer followUserById(@Param(value = "uid") Integer uid, @Param(value = "fid") Integer fid);


    /**
     * 查看follow关系是否已经存在
     * @param uid 被follow的作者的id
     * @param fid follow的id
     * @return
     */
    public Integer findFollowRelation(@Param(value = "uid") Integer uid, @Param(value = "fid") Integer fid);

    /**
     * 通过给定的文章id来寻找评论
     * @param uids  给定的uid数组
     * @return
     */
    public List<User> findUsersById(@Param(value = "uids")List<Integer> uids);

    public List<CommentWithWriter> findCommentWithWriterByArticleId(@Param(value = "aid")Integer aid);

    public List<User> getAllMyFollowers(@Param(value = "uid")Integer id);
}
